public class Servicio_Cliente {
    
    private Integer id_sc;
    private Integer id_servicio;
    private Integer cod_trabajador;
    private Integer num_habitacion;
    private Double importe;

    public Servicio_Cliente(){}

    public Servicio_Cliente(Integer id_sc,Integer id_servicio,Integer cod_trabajador,Integer num_habitacion,Double importe){
        this.cod_trabajador = cod_trabajador;
        this.id_sc = id_sc;
        this.id_servicio = id_servicio;
        this.importe = importe;
        this.num_habitacion = num_habitacion;
    }

    public Integer getId_sc() {
        return id_sc;
    }

    public void setId_sc(Integer id_sc) {
        this.id_sc = id_sc;
    }

    public Integer getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Integer getCod_trabajador() {
        return cod_trabajador;
    }

    public void setCod_trabajador(Integer cod_trabajador) {
        this.cod_trabajador = cod_trabajador;
    }

    public Integer getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(Integer num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }


    
}
