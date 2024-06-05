public class Trabajador {
    private Integer cod_trabajador;
    private String nom_trabajador;

    public Trabajador(){}

    public Trabajador(Integer cod_trabajador, String nom_trabajador){
        this.cod_trabajador = cod_trabajador;
        this.nom_trabajador = nom_trabajador;
    }

    public Integer getCod_trabajador() {
        return cod_trabajador;
    }

    public void setCod_trabajador(Integer cod_trabajador) {
        this.cod_trabajador = cod_trabajador;
    }

    public String getNom_trabajador() {
        return nom_trabajador;
    }

    public void setNom_trabajador(String nom_trabajador) {
        this.nom_trabajador = nom_trabajador;
    }


    
}
