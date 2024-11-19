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
    private Eventos eventos;  
    private JCalendar calendario; 
    public VentanaDeCalendario(Eventos eventos) {
        this.eventos = eventos;  

        setTitle("Calendario de Eventos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        calendario = new JCalendar();

        marcarDiasConEventos();

        calendario.getDayChooser().addPropertyChangeListener("day", e -> {
            Date fechaSeleccionada = calendario.getDate();
            mostrarEventos(fechaSeleccionada);
        });

        setLayout(new BorderLayout());
        add(calendario, BorderLayout.CENTER);
    }

    private void marcarDiasConEventos() {
        var dayChooser = calendario.getDayChooser();

        for (Component component : dayChooser.getDayPanel().getComponents()) {
            if (component instanceof JButton boton) {
                boton.setBackground(UIManager.getColor("Button.background"));
            }
        }

        for (Evento evento : eventos.getListaEventos()) {
            LocalDate fechaEvento = evento.getFechaComoLocalDate();
            Date fechaDate = Date.from(fechaEvento.atStartOfDay(ZoneId.systemDefault()).toInstant());

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
                            boton.setBackground(Color.YELLOW); 
                        }
                    } catch (NumberFormatException ignored) {
                        
                    }
                }
            }
        }
    }

    private void mostrarEventos(Date fechaSeleccionada) {
        LocalDate fecha = fechaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        List<Evento> eventosDelDia = eventos.getEventosEnFecha(fecha);

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
