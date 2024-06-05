public class Servicio {
    private Integer id_servicio;
    private Integer cod_grupo;
    private String detalle;
    private Double precio;

    public Servicio(){}

    public Servicio(Integer id_servicio,Integer cod_grupo,String detalle,Double precio){
        this.cod_grupo = cod_grupo;
        this.detalle = detalle;
        this.id_servicio = id_servicio;
        this.precio = precio;
    }

    public Integer getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Integer getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(Integer cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    
}
