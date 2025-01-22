package com.edutecno.sistemacalificaciones.model;

import java.util.Objects;
import java.util.Set;

public class UserResponseDTO {
    private Long userId;
    private String username;
    private String rut;
    private String email;
    private Boolean isActive;
    private Set<String> roles;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long userId, String username, String rut, String email, Boolean isActive, Set<String> roles) {
        this.userId = userId;
        this.username = username;
        this.rut = rut;
        this.email = email;
        this.isActive = isActive;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(rut, that.rut) && Objects.equals(email, that.email) && Objects.equals(isActive, that.isActive) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, rut, email, isActive, roles);
    }
}
