package Recursos;

public class Recursos {
    private boolean salones;
    private boolean equipoAudiovisual;
    private boolean catering;

    public Recursos(boolean salones, boolean equipoAudiovisual, boolean catering) {
        this.salones = salones;
        this.equipoAudiovisual = equipoAudiovisual;
        this.catering = catering;
    }

    @Override
    public String toString() {
        return salones + "," + equipoAudiovisual + "," + catering;
    }

    public boolean isSalones() {
        return salones;
    }

    public void setSalones(boolean salones) {
        this.salones = salones;
    }

    public boolean isEquipoAudiovisual() {
        return equipoAudiovisual;
    }

    public void setEquipoAudiovisual(boolean equipoAudiovisual) {
        this.equipoAudiovisual = equipoAudiovisual;
    }

    public boolean isCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public String getRecursos() {
        String recursos = "";
        if (salones) {
            recursos += "Salones, ";
        }
        if (equipoAudiovisual) {
            recursos += "Equipo Audiovisual, ";
        }
        if (catering) {
            recursos += "Catering, ";
        }
        return recursos;
    }

    // Método para convertir los recursos en una cadena de texto
    public String recursosToString() {
        return salones + "," + equipoAudiovisual + "," + catering;
    }

    // Método para reconstruir un objeto Recursos a partir de una cadena
    public static Recursos fromString(String line) {
        String[] data = line.split(",");
        if (data.length == 3) {
            boolean salones = Boolean.parseBoolean(data[0]);
            boolean equipoAudiovisual = Boolean.parseBoolean(data[1]);
            boolean catering = Boolean.parseBoolean(data[2]);
            return new Recursos(salones, equipoAudiovisual, catering);
        }
        return null;
    }
}
