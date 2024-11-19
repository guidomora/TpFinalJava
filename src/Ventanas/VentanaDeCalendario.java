package Ventanas;

import Evento.Evento;
import Eventos.Eventos;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class VentanaDeCalendario extends JFrame {
    private Eventos eventos;  // Lista de eventos
    private JCalendar calendario; // Componente de calendario

    public VentanaDeCalendario(Eventos eventos) {
        this.eventos = eventos;  // Asignar eventos al calendario

        setTitle("Calendario de Eventos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el componente JCalendar
        calendario = new JCalendar();

        // Colorear los días con eventos
        marcarDiasConEventos();

        // Agregar un evento de cambio de fecha
        calendario.getDayChooser().addPropertyChangeListener("day", e -> {
            Date fechaSeleccionada = calendario.getDate();
            mostrarEventos(fechaSeleccionada);
        });

        // Layout principal
        setLayout(new BorderLayout());
        add(calendario, BorderLayout.CENTER);
    }

    private void marcarDiasConEventos() {
        // Obtener el modelo del calendario
        var dayChooser = calendario.getDayChooser();

        // Resetear colores
        for (Component component : dayChooser.getDayPanel().getComponents()) {
            if (component instanceof JButton boton) {
                boton.setBackground(UIManager.getColor("Button.background"));
            }
        }

        // Marcar días con eventos
        for (Evento evento : eventos.getListaEventos()) {
            LocalDate fechaEvento = evento.getFechaComoLocalDate();
            Date fechaDate = Date.from(fechaEvento.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Comparar la fecha del evento con los días visibles en el calendario
            for (Component component : dayChooser.getDayPanel().getComponents()) {
                if (component instanceof JButton boton) {
                    try {
                        int dia = Integer.parseInt(boton.getText());
                        LocalDate fechaBoton = LocalDate.of(
                                calendario.getYearChooser().getYear(),
                                calendario.getMonthChooser().getMonth() + 1,
                                dia
                        );
                        if (fechaEvento.equals(fechaBoton)) {
                            boton.setBackground(Color.YELLOW); // Marcar día con evento
                        }
                    } catch (NumberFormatException ignored) {
                        // Los botones vacíos o no válidos se ignoran
                    }
                }
            }
        }
    }

    private void mostrarEventos(Date fechaSeleccionada) {
        // Convertir la fecha seleccionada a LocalDate
        LocalDate fecha = fechaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Obtener eventos en la fecha seleccionada
        List<Evento> eventosDelDia = eventos.getEventosEnFecha(fecha);

        // Construir la lista de eventos
        if (eventosDelDia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay eventos para esta fecha.", "Eventos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder mensaje = new StringBuilder("Eventos del " + fecha + ":\n");
            for (Evento evento : eventosDelDia) {
                mensaje.append("- ").append(evento.getDescripcion()).append("\n");
            }
            JOptionPane.showMessageDialog(this, mensaje.toString(), "Eventos", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
