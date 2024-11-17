package Evento;

import Recursos.Recursos;

public class Evento {
    private String descripcion;
    private String fecha;
    private String ubicacion;
    private int capacidad;
    private String categoria;
    private Recursos recursos;

    public Evento(String descripcion, String fecha, String ubicacion, int capacidad, Recursos recursos ) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.categoria = asignarCategoria(capacidad);
        this.recursos = recursos;

    }

    private String asignarCategoria(int capacidad) {
        if (capacidad < 400) {
            return "Pequeño";
        } else if (capacidad <= 1200) {
            return "Mediano";
        } else {
            return "Grande";
        }
    }

    public String getEvento() {
        return "Descripción: " + descripcion + " " + 
               "Fecha: " + fecha + " " + 
               "Ubicación: " + ubicacion + " " + 
               "Capacidad: " + capacidad + " " + 
               "Categoría: " + categoria
               + "Recursos: " + recursos.getRecursos();
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

    public Recursos getRecursos() {
        return recursos;
    }

    @Override
    public String toString() {
        return descripcion;  // Usamos solo la descripción para mostrarla en la lista
    }
}
