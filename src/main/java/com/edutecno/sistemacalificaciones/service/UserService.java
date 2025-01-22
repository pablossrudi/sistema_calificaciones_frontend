package com.edutecno.sistemacalificaciones.service;

import com.edutecno.sistemacalificaciones.model.AuthenticationRequest;
import com.edutecno.sistemacalificaciones.model.AuthenticationResponse;
import com.edutecno.sistemacalificaciones.model.UserCreateDTO;
import com.edutecno.sistemacalificaciones.model.UserResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api";

    @Value("${jwt.header:Authorization}")
    private String tokenHeader;

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        String url = URL + "/auth/login";
        return restTemplate.postForObject(url, request, AuthenticationResponse.class);
    }

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        String url = URL + "/auth/register";
        return restTemplate.postForObject(url, userCreateDTO, UserResponseDTO.class);
    }

    public List<UserResponseDTO> getAllUsers(String token) {
        String url = URL + "/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set(tokenHeader, "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<UserResponseDTO[]> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity, UserResponseDTO[].class
        );

        return Arrays.asList(response.getBody());
    }


}
