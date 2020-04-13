package posmotriKa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import posmotriKa.models.Message;
import posmotriKa.models.User;
import posmotriKa.repositories.MessageRepository;
import posmotriKa.security.UserDetailsImpl;

import java.net.Authenticator;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class ChatController {

    @Autowired
    MessageRepository messageRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat")
    public String getChatPage(Model model) {

        String pageId = UUID.randomUUID().toString();
        model.addAttribute("pageId", pageId);

        List<Message> list = messageRepository.findAll();
        Collections.reverse(list);
        model.addAttribute("oldMessages", list);

        return "chat";
    }
}
