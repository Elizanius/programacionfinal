public class Grupo {
    private Integer cod_grupo;
    private String nom_servicio;

    public Grupo(){}

    public Grupo(Integer cod_grupo, String nom_servicio){
        this.cod_grupo = cod_grupo;
        this.nom_servicio = nom_servicio;
    }

    public Integer getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(Integer cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public String getNom_servicio() {
        return nom_servicio;
    }

    public void setNom_servicio(String nom_servicio) {
        this.nom_servicio = nom_servicio;
    }


    
}
