package Asistentes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public static void guardarAsistentes(List<Asistente> asistentes, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(asistentes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Asistente> cargarAsistentes(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (List<Asistente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
