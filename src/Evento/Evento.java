package Evento;

public class Evento {
    private String descripcion;
    private String fecha;
    private String ubicacion;
    private int capacidad;
    private String categoria;

    public Evento(String descripcion, String fecha, String ubicacion, int capacidad, String categoria) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.categoria = categoria;
    }

    public String getEvento(){
        return descripcion + " " + fecha + " " + ubicacion + " " + capacidad + " " + categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
