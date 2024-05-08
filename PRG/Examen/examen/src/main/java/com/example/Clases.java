package com.example;

public class Clases {
    
    public static class Cliente{
         
        static String nombre;
        static String apellidos;
        static String clave;
        static String NIF;
        static String movil;

        public static String getNombre() {
            return nombre;
        }
        public static void setNombre(String nombre) {
            Cliente.nombre = nombre;
        }
        public static String getApellidos() {
            return apellidos;
        }
        public static void setApellidos(String apellidos) {
            Cliente.apellidos = apellidos;
        }
        public static String getClave() {
            return clave;
        }
        public static void setClave(String clave) {
            Cliente.clave = clave;
        }
        public static String getNIF() {
            return NIF;
        }
        public static void setNIF(String nIF) {
            NIF = nIF;
        }
        public static String getMovil() {
            return movil;
        }
        public static void setMovil(String movil) {
            Cliente.movil = movil;
        }

    }

    public static class Cuenta{
        
    } 
}
