package posmotriKa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import posmotriKa.dto.ProfileForm;
import posmotriKa.dto.RegisterForm;
import posmotriKa.services.RegisterService;

import javax.validation.Valid;


@Controller
public class RegisterController {
    @Autowired
    private RegisterService service;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterForm form, BindingResult bindingResult, Model model) {
        service.register(form);
        System.out.println(bindingResult.getAllErrors());
        model.addAttribute("registerForm", form);
        return "redirect:/login";
    }
}
