package com.example.Clases;

    public class Factura{

        private String num_fra;
        private String NIF;
        private int num_habitacion;
        private double importe;

        public String getNum_fra() {
            return num_fra;
        }
        public void setNum_fra(String num_fra) {
            this.num_fra = num_fra;
        }
        public String getNIF() {
            return NIF;
        }
        public void setNIF(String nIF) {
            NIF = nIF;
        }
        public int getNum_habitacion() {
            return num_habitacion;
        }
        public void setNum_habitacion(int num_habitacion) {
            this.num_habitacion = num_habitacion;
        }
        public double getImporte() {
            return importe;
        }
        public void setImporte(double importe) {
            this.importe = importe;
        }

   }
