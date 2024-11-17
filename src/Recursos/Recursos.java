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
}
