package com.example.Clases;

public class Cliente{
         
    private Double dinero_ingresado;
    private Double dinero_gastado;
    private String clave;
    private String NIF;
    

    public Cliente(Double dinero_ingresado,Double dinero_gastado,String NIF, String clave){
        this.dinero_ingresado = dinero_ingresado;
        this.dinero_gastado = dinero_gastado;
        this.clave = clave;
        this.NIF = NIF;
    }

    public Double getDinero_gastado() {
        return dinero_gastado;
    }
    public void setDinero_ingresado(Double dinero_gastado) {
        this.dinero_gastado = dinero_gastado;
    }
    public Double getDinero_ingresado() {
        return dinero_ingresado;
    }
    public void setApellidos(Double dinero_ingresado) {
        this.dinero_ingresado = dinero_ingresado;
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

}
