package com.example.Clases;

public class Cuenta{

    private String num_cta;
    private String NIF;
    private double saldo;
   public Cuenta(){}
   public Cuenta(String num_cta,String NIF,double saldo){
       this.num_cta = num_cta;
       this.NIF = NIF;
       this.saldo = saldo;
   }
   
   public String getNum_cta() {
       return num_cta;
   }
   public void setNum_cta(String num_cta) {
       this.num_cta = num_cta;
   }
   public String getNIF() {
       return NIF;
   }
   public void setNIF(String nIF) {
       NIF = nIF;
   }
   public double getSaldo() {
       return saldo;
   }
   public void setSaldo(double saldo) {
       this.saldo = saldo;
   }
}

