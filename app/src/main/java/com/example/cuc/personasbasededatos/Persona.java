package com.example.cuc.personasbasededatos;

/**
 * Created by CUC on 12/05/2017.
 */

public class Persona {
    private String foto;
    private String nombre;
    private String apellido;
    private String sexo;
    private String pasatiempo;

    public Persona(String foto, String nombre, String apellido, String sexo, String pasatiempo) {
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.pasatiempo = pasatiempo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPasatiempo() {
        return pasatiempo;
    }

    public void setPasatiempo(String pasatiempo) {
        this.pasatiempo = pasatiempo;
    }
}
