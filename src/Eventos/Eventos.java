package Eventos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Asistente.Asistente;
import Evento.Evento;

public class Eventos {
    private List<Evento> eventos;

    public Eventos() {
        this.eventos = new ArrayList<>();
        leerEventosDesdeArchivo("eventos.txt"); // Cargar los eventos desde el archivo
    }

    public void addEvento(Evento evento) {
        eventos.add(evento);
        guardarEventosEnArchivo("eventos.txt");
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
        guardarEventosEnArchivo("eventos.txt");
    }

    

    public List<Evento> getEventosEnFecha(java.util.Date fecha) {
        List<Evento> eventosEnFecha = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.getFecha().equals(fecha.toString())) { // Compara la fecha
                eventosEnFecha.add(evento);
            }
        }
        return eventosEnFecha;
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

    public void leerEventosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Evento evento = Evento.fromString(line); // Usamos el método fromString para crear el evento
                if (evento != null && !eventos.contains(evento)) {  // Evitar duplicados
                    addEvento(evento); // Agregar el evento a la lista
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void guardarEventosEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            for (Evento evento : eventos) {
                writer.write(evento.toString());  // Escribir evento
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    


}
