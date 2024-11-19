package Asistentes;

import java.util.List;

import Asistente.Asistente;
import Eventos.Eventos;

public class Asistentes {
    private List<Asistente> asistentes;
    private Eventos eventos;

    public Asistentes(List<Asistente> asistentes) {
        this.asistentes = asistentes;
    }

    

    public String getAsistentes() {
        StringBuilder resultado = new StringBuilder();
        for (Asistente asistente : asistentes) {
            resultado.append(asistente.getAsistente()).append("\n");
        }
        return resultado.toString();
    }

    public List<Asistente> getListaAsistentes() {
        return asistentes;
    }
    
    public List<Asistente> getListaAsistentesDos() {
        return this.asistentes;
    }
    

    public void addAsistente(Asistente asistente) {
        asistentes.add(asistente);
    }

    public void removeAsistente(Asistente asistente) {
        asistentes.remove(asistente);
    }

    public int getCantidadAsistentes() {
        return asistentes.size();
    }
}
