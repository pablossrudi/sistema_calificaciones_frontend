package com.edutecno.sistemacalificaciones.service;

import com.edutecno.sistemacalificaciones.model.AlumnoCreateDTO;
import com.edutecno.sistemacalificaciones.model.AlumnoResponseDTO;
import com.edutecno.sistemacalificaciones.model.PageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoService {

    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api";

    public AlumnoService(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    @Value("${jwt.header:Authorization}")
    private String tokenHeader;

    public AlumnoResponseDTO createAlumno(AlumnoCreateDTO alumnoCreateDTO, String token) {
        String url = URL + "/alumnos";

        HttpHeaders headers = new HttpHeaders();
        headers.set(tokenHeader, "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(alumnoCreateDTO, headers);

        System.out.println("token: " + token);
        System.out.println("URL a llamar: " + url);
        // Realizamos la solicitud POST
        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class // Como no hay cuerpo en la respuesta, usamos Void
        );

        // Verificamos el estado de la respuesta
        if (response.getStatusCode() == HttpStatus.CREATED) {
            // El alumno fue creado correctamente
            return new AlumnoResponseDTO(); // Aquí podrías devolver un nuevo objeto o manejarlo de alguna otra forma
        } else {
            // En caso de error, lanzamos una excepción o devolvemos null
            System.out.println("Error al crear el alumno: " + response.getStatusCode());
            return null;
        }
    }

    public List<AlumnoResponseDTO> getAllAlumnos(String token, int page, int size) {
        String url = String.format("%s/alumnos?page=%d&size=%d", URL, page, size);
        System.out.println("token: " + token);
        System.out.println("URL a llamar: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.set(tokenHeader, "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            // Usar ParameterizedTypeReference para manejar la respuesta paginada
            ResponseEntity<PageResponse<AlumnoResponseDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            System.out.println("Respuesta: " + response.getStatusCode());
            return response.getBody() != null ? response.getBody().getAlumnos() : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error al llamar al metodo: " + e);
            throw e;
        }
    }
}
