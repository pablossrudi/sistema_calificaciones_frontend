package com.edutecno.sistemacalificaciones.controller;

import com.edutecno.sistemacalificaciones.model.UserResponseDTO;
import com.edutecno.sistemacalificaciones.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model, HttpSession session) {
        // Verificar si el usuario está autenticado
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "redirect:/auth/login";
        }

        try {
            // Obtener la lista de usuarios
            List<UserResponseDTO> users = userService.getAllUsers(token);
            model.addAttribute("users", users);

            // Agregar el rol para mostrar/ocultar funcionalidades según permisos
            String role = (String) session.getAttribute("role");
            model.addAttribute("userRole", role);

            return "alumnos/index";
        } catch (Exception e) {
            // Si hay un error con el token, redirigir al login
            session.invalidate();
            return "redirect:/auth/login";
        }
    }
}