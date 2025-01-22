package com.edutecno.sistemacalificaciones.controller;

import com.edutecno.sistemacalificaciones.model.AuthenticationRequest;
import com.edutecno.sistemacalificaciones.model.AuthenticationResponse;
import com.edutecno.sistemacalificaciones.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new AuthenticationRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") AuthenticationRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            AuthenticationResponse response = userService.login(request);

            // GUARDAMOS TOKEN Y ROL EN LA SESION
            session.setAttribute("token", response.getJwt());
            session.setAttribute("role", response.getRole());
            System.out.println(response.getRole());
            return "redirect:/alumnos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Credenciales invalidas");
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

}
