package Evento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Asistente.Asistente;
import Asistentes.Asistentes;
import Recursos.Recursos;

public class Evento {
    private String descripcion;
    private String fecha;
    private String ubicacion;
    private int capacidad;
    private String categoria;
    private Recursos recursos;
    private Asistentes asistentes; // Ahora tienes un objeto Asistentes

    public Evento(String descripcion, String fecha, String ubicacion, int capacidad, Recursos recursos) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.categoria = asignarCategoria(capacidad);
        this.recursos = recursos;
        this.asistentes = new Asistentes(new ArrayList<>()); // Inicializa la lista de asistentes
    }

    public String asignarCategoria(int capacidad) {
        if (capacidad < 400) {
            return "Pequeño";
        } else if (capacidad <= 1200) {
            return "Mediano";
        } else {
            return "Grande";
        }
    }

    // Este método ahora solo devuelve la cantidad de asistentes
    public String getEvento() {
        return "Descripción: " + descripcion + " " +
                "Fecha: " + fecha + " " +
                "Ubicación: " + ubicacion + " " +
                "Capacidad: " + capacidad + " " +
                "Categoría: " + categoria + " " +
                "Recursos: " + recursos.getRecursos() + " " +
                "Cantidad de asistentes: " + " " + asistentes.getCantidadAsistentes(); // Muestra solo la cantidad de
                                                                                       // asistentes
    }

    public void addAsistente(Asistente asistente) {
        this.asistentes.addAsistente(asistente); // Agregar un asistente al evento
    }

    public Asistentes getAsistentes() {
        return asistentes; // Para acceder a los asistentes del evento
    }

    public List<Asistente> getListaAsistentes() {
        return this.asistentes.getListaAsistentes(); // Usa el método de la clase Asistentes
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion + "," + fecha + "," + ubicacion + "," + capacidad + "," + asistentes.getListaAsistentes()
                + "," + categoria + ","
                + recursos.toString();
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public LocalDate getFechaComoLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return LocalDate.parse(fecha, formatter);
    }

    public static Evento fromString(String line) {
        String[] data = line.split(","); // Asumiendo que los datos están separados por comas
        if (data.length == 5) { // Asegúrate de que haya 5 elementos en la línea
            String descripcion = data[0];
            String fecha = data[1];
            String ubicacion = data[2];
            int capacidad = Integer.parseInt(data[3]);
            Recursos recursos = Recursos.fromString(data[4]); // Llama al método fromString de Recursos
            return new Evento(descripcion, fecha, ubicacion, capacidad, recursos);
        }
        return null; // Si no hay 5 elementos, retornamos null
    }

}
