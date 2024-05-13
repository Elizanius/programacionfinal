package com.example.Clases;

public class Cliente{
         
    private String nombre;
    private String apellidos;
    private String clave;
    private String NIF;
    private String movil;

    public Cliente(String nombre,String apellidos,String clave,String NIF, String movil){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        this.NIF = NIF;
        this.movil = movil;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNIF() {
        return NIF;
    }
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
    public String getMovil() {
        return movil;
    }
    public void setMovil(String movil) {
        this.movil = movil;
    }

}
