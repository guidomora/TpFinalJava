package Eventos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Asistente.Asistente;
import Evento.Evento;
import Recursos.Recursos;

public class Eventos {
    private List<Evento> eventos;

    public Eventos() {
        this.eventos = new ArrayList<>();
    }

    public void addEvento(Evento evento) {
        if (!eventos.contains(evento)) {
            eventos.add(evento);
        } 
    }
    

    public void getEventos() {
        for (Evento evento : eventos) {
            System.out.println(evento.getEvento());
        }
    }

    public List<Evento> getListaEventos() {
        return eventos;
    }

    public void removeEvento(Evento evento) {
        eventos.remove(evento);
        cargarEventosDesdeArchivo("eventos.txt");
    }

    public List<Evento> getEventosEnFecha(LocalDate fecha) {
        List<Evento> eventosDelDia = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.getFechaComoLocalDate().equals(fecha)) {
                eventosDelDia.add(evento);
            }
        }
        return eventosDelDia;
    }

    

    public void cargarEventosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String descripcion = datos[0];
                String fecha = datos[1];
                String ubicacion = datos[2];
                int capacidad = Integer.parseInt(datos[3]);
                Recursos recursos = new Recursos(
                        Boolean.parseBoolean(datos[5]),
                        Boolean.parseBoolean(datos[6]),
                        Boolean.parseBoolean(datos[7]));
                Evento evento = new Evento(descripcion, fecha, ubicacion, capacidad, recursos);
                this.addEvento(evento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarEventosEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, false))) {
            for (Evento evento : eventos) {
                writer.write(evento.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los eventos: " + e.getMessage());
        }
    }

}
