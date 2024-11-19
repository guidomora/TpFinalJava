package Evento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    private Asistentes asistentes;

    public Evento(String descripcion, String fecha, String ubicacion, int capacidad, Recursos recursos) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.categoria = asignarCategoria(capacidad);
        this.recursos = recursos;
        this.asistentes = new Asistentes(new ArrayList<>()); 
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

    public String getEvento() {
        return "Descripción: " + descripcion + " " +
                "Fecha: " + fecha + " " +
                "Ubicación: " + ubicacion + " " +
                "Capacidad: " + capacidad + " " +
                "Categoría: " + categoria + " " +
                "Recursos: " + recursos.getRecursos() + " " +
                "Cantidad de asistentes: " + " " + asistentes.getCantidadAsistentes(); 
    }

    public void addAsistente(Asistente asistente) {
        if (!this.asistentes.getListaAsistentes().contains(asistente)) { 
            this.asistentes.addAsistente(asistente); 
        }
    }
    

    public Asistentes getAsistentes() {
        return asistentes; 
    }

    public List<Asistente> getListaAsistentes() {
        return this.asistentes.getListaAsistentes(); 
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        StringBuilder asistentesStr = new StringBuilder();
        for (Asistente asistente : asistentes.getListaAsistentes()) {
            asistentesStr.append(asistente.getNombre()).append(",")
                    .append(asistente.getApellido()).append(",")
                    .append(asistente.getCorreo()).append(",")
                    .append(asistente.getEdad()).append(", ");
        }
        if (asistentesStr.length() > 0) {
            asistentesStr.setLength(asistentesStr.length() - 2);
        }

        return descripcion + "," + fecha + "," + ubicacion + "," + capacidad + ",[" + asistentesStr + "],"
                + categoria + "," + recursos.toString();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Evento evento = (Evento) obj;
        return descripcion.equals(evento.descripcion) &&
                fecha.equals(evento.fecha) &&
                ubicacion.equals(evento.ubicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, fecha, ubicacion);
    }

    public static Evento fromString(String line) {
        try {
            String[] data = line.split(",");
            if (data.length >= 6) { 
                String descripcion = data[0];
                String fecha = data[1];
                String ubicacion = data[2];
                int capacidad = Integer.parseInt(data[3]);

                String asistentesStr = data[4];

                String categoria = data[5];

                String[] recursosData = Arrays.copyOfRange(data, 6, data.length);
                Recursos recursos = Recursos.fromString(String.join(",", recursosData));

                Evento evento = new Evento(descripcion, fecha, ubicacion, capacidad, recursos);

                if (!asistentesStr.equals("[]")) {
                    String[] asistentesDatos = asistentesStr.replaceAll("[\\[\\]]", "").split(", ");
                    for (int i = 0; i < asistentesDatos.length; i += 4) {
                        if (i + 3 < asistentesDatos.length) { 
                            Asistente asistente = new Asistente(
                                    asistentesDatos[i],            
                                    asistentesDatos[i + 1],         
                                    asistentesDatos[i + 2],         
                                    "123456775",                    
                                    Integer.parseInt(asistentesDatos[i + 3]) 
                            );
                            evento.addAsistente(asistente);
                        }
                    }
                }
                

                return evento;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
