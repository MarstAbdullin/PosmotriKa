package posmotriKa.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import posmotriKa.models.Message;
import posmotriKa.models.User;
import posmotriKa.repositories.MessageRepository;
import posmotriKa.security.UserDetailsImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MessagesRestController {
    private static final Map<String, List<Message>> messages = new HashMap<>();

    @Autowired
    MessageRepository messageRepository;

    // получили сообщение от какой либо страницы - мы его разошлем во все другие страницы
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(Authentication authentication, @RequestBody Message message) {

        authentication.getDetails();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        messageRepository.save(Message.builder()
                .pageId(message.getPageId())
                .text(message.getText())
                .user(user)
                .build());

        // если сообщений с этой или для этой страницы еще не было
        if (!messages.containsKey(message.getPageId())) {
            // добавляем эту страницу в Map-у страниц
            messages.put(message.getPageId(), new ArrayList<>());
        }
        // полученное сообщение добавляем для всех открытых страниц нашего приложения
        for (List<Message> pageMessages : messages.values()) {
            // перед тем как положить сообщение для какой-либо страницы
            // мы список сообщений блокируем
            synchronized (pageMessages) {
                // добавляем сообщение
                pageMessages.add(message);
                // говорим, что другие потоки могут воспользоваться этим списком
                pageMessages.notifyAll();
            }
        }

        return ResponseEntity.ok().build();
    }

    // получить все сообщения для текущего запроса
    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesForPage(@RequestParam("pageId") String pageId) {
        // получили список сообшений для страницы и заблокировали его
        synchronized (messages.get(pageId)) {
            // если нет сообщений уходим в ожидание
            if (messages.get(pageId).isEmpty()) {
                messages.get(pageId).wait();
            }
        }

        // если сообщения есть - то кладем их в новых список
        List<Message> response = new ArrayList<>(messages.get(pageId));
        // удаляем сообщения из мапы
        messages.get(pageId).clear();
        return ResponseEntity.ok(response);
    }
}
