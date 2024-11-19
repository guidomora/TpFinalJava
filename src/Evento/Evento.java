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
        if (!this.asistentes.getListaAsistentes().contains(asistente)) { // Evita duplicados
            this.asistentes.addAsistente(asistente); // Agrega el asistente
        }
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
        StringBuilder asistentesStr = new StringBuilder();
        for (Asistente asistente : asistentes.getListaAsistentes()) {
            asistentesStr.append(asistente.getNombre()).append(",")
                    .append(asistente.getApellido()).append(",")
                    .append(asistente.getCorreo()).append(",")
                    .append(asistente.getEdad()).append(", ");
        }
        // Remover la última coma y espacio si hay asistentes
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
            if (data.length >= 6) { // Asegúrate de tener al menos 6 elementos
                String descripcion = data[0];
                String fecha = data[1];
                String ubicacion = data[2];
                int capacidad = Integer.parseInt(data[3]);

                // Parsear la lista de asistentes (si existe)
                String asistentesStr = data[4];

                String categoria = data[5];

                // Crear recursos desde el final de la línea
                String[] recursosData = Arrays.copyOfRange(data, 6, data.length);
                Recursos recursos = Recursos.fromString(String.join(",", recursosData));

                Evento evento = new Evento(descripcion, fecha, ubicacion, capacidad, recursos);

                // Si hay asistentes, agregarlos
                if (!asistentesStr.equals("[]")) {
                    String[] asistentesDatos = asistentesStr.replaceAll("[\\[\\]]", "").split(", ");
                    for (int i = 0; i < asistentesDatos.length; i += 4) {
                        if (i + 3 < asistentesDatos.length) { // Verifica que haya suficientes datos para crear un asistente
                            Asistente asistente = new Asistente(
                                    asistentesDatos[i],            
                                    asistentesDatos[i + 1],         
                                    asistentesDatos[i + 2],         
                                    "123456775",                    
                                    Integer.parseInt(asistentesDatos[i + 3]) // Edad
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
