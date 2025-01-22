package com.edutecno.sistemacalificaciones.model;

public class UserChangeStatusDTO {
    private Boolean isActive;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserChangeStatusDTO{" +
            "isActive=" + isActive +
            '}';
    }
}
