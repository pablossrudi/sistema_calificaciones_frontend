package com.edutecno.sistemacalificaciones.controller;

import com.edutecno.sistemacalificaciones.model.AlumnoCreateDTO;
import com.edutecno.sistemacalificaciones.model.AlumnoResponseDTO;
import com.edutecno.sistemacalificaciones.service.AlumnoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public String getAllAlumnos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            Model model,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "redirect:/auth/login";
        }

        try {
            List<AlumnoResponseDTO> alumnos = alumnoService.getAllAlumnos(token, page, size);
            String role = (String) session.getAttribute("role");

            model.addAttribute("alumnos", alumnos);
            model.addAttribute("alumnoRole", role);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageSize", size);
            System.out.println(alumnos);
            return "alumnos/index";
        } catch (Exception e) {
            session.invalidate();
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/create")
    public String getAlumnos(Model model, HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "redirect:/auth/login";
        }

        String rol = (String) session.getAttribute("role");

        if (Objects.equals(rol, "ROLE_CLIENT")) {
            return "redirect:/alumnos";
        }

        model.addAttribute("createAlumnoRequest", new AlumnoCreateDTO());

        return "alumnos/crearAlumnos";
    }

    @PostMapping("/create")
    public String createAlumno(@ModelAttribute("createAlumnoRequest") AlumnoCreateDTO alumnoCreateDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        String token = (String) session.getAttribute("token");

        var hola = alumnoService.createAlumno(alumnoCreateDTO, token);
        System.out.println(hola);
        return "redirect:/alumnos";
    }

}
