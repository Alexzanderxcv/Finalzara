/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

/**
 *
 * @author melan
 */
    public class LoginResponse {
    private String rol;
    private Long idRol;

    public LoginResponse(String rol, Long idRol) {
        this.rol = rol;
        this.idRol = idRol;
    }
   public LoginResponse() {}

    // Getters y Setters
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getIdrol() {
        return idRol;
    }

    public void setIdrol(Long idRol) {
        this.idRol = idRol;
    }
}

    

