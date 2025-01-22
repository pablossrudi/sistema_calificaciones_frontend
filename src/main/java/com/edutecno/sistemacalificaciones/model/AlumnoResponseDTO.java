package com.edutecno.sistemacalificaciones.model;

import java.util.Set;

public class AlumnoResponseDTO {
    private Long alumnoId;
    private String rut;
    private String nombre;
    private String direccion;
    private Set<String> materias;

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<String> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<String> materias) {
        this.materias = materias;
    }

//        public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getRut() {
//        return rut;
//    }
//
//    public void setRut(String rut) {
//        this.rut = rut;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public Set<String> getMaterias() {
//        return materias;
//    }
//
//    public void setMaterias(Set<String> materias) {
//        this.materias = materias;
//    }
}
