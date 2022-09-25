package com.example.tp3_pa_grupo3;

public class Usuarios {
    private int ID;
    private String Nombre;
    private String Correo;
    private String Contrasenia;

    public Usuarios(){

    }

    public Usuarios(int ID, String nombre, String correo, String contrasenia) {
        this.ID = ID;
        Nombre = nombre;
        Correo = correo;
        Contrasenia = contrasenia;
    }

    public Usuarios(String correo, String contrasenia) {
        this.ID = 0;
        this.Nombre = "";
        Correo = correo;
        Contrasenia = contrasenia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }
}
