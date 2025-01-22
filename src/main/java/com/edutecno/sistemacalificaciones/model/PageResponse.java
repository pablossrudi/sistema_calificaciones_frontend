package com.edutecno.sistemacalificaciones.model;

import java.util.List;

public class PageResponse<T> {
    private int numberOfElements;
    private List<T> alumnos; // Cambiar content por alumnos
    private int numberPage;

    // Getters y setters
    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<T> alumnos) {
        this.alumnos = alumnos;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }
}
