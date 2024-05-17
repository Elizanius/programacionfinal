package com.example;

public class Producto {
    private Integer id;
    private String nombre;
    private Double precio_compra;
    private Double precio_venta;
    private Integer stock;
    

    public Producto(Integer id,String nombre,Double precio_compra, Double precio_venta, Integer stock){
        this.id = id;
        this.nombre = nombre;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.stock = stock;
    }

    public Producto(){}

    public Producto(Integer id,String nombre,Integer stock,Double precio_venta){
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio_venta = precio_venta;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Double getPrecio_compra() {
        return precio_compra;
    }


    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra = precio_compra;
    }


    public Double getPrecio_venta() {
        return precio_venta;
    }


    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }


    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }


}
