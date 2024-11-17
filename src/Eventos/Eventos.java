package Eventos;

import java.util.ArrayList;
import java.util.List;

import Evento.Evento;

public class Eventos {
    private List<Evento> eventos;

    public Eventos() {
        this.eventos =  new ArrayList<>();
    }

    public void addEvento(Evento evento) {
        eventos.add(evento);
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
    }

    public List<Evento> getEventosEnFecha(java.util.Date fecha) {
        List<Evento> eventosEnFecha = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.getFecha().equals(fecha.toString())) {  // Compara la fecha
                eventosEnFecha.add(evento);
            }
        }
        return eventosEnFecha;
    }
}
