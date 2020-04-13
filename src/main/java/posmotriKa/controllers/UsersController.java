package posmotriKa.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String getRoot() {
        return "users";
    }
}
