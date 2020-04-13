package posmotriKa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import posmotriKa.dto.RegisterDto;
import posmotriKa.services.RegisterService;


@Controller
public class RegisterController {
    @Autowired
    private RegisterService service;

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(RegisterDto form) {
        service.register(form);
        return "redirect:/login";
    }
}
