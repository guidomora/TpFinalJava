package Ventanas;
import Evento.Evento;
import Eventos.Eventos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class VentanaDeCalendario extends JFrame {
    private Eventos eventos;  // Para almacenar la lista de eventos

    // Constructor que recibe el objeto eventos
    public VentanaDeCalendario(Eventos eventos) {
        this.eventos = eventos;  // Asignar el objeto eventos

        // Mostrar la ventana del calendario
        SwingUtilities.invokeLater(() -> new CalendarioFrame(eventos).setVisible(true));
    }
}


class CalendarioFrame extends JFrame {
    private JPanel calendarioPanel;
    private LocalDate fechaActual;
    private Eventos eventos;  // La lista de eventos

    // Constructor que recibe el objeto eventos
    public CalendarioFrame(Eventos eventos) {
        this.eventos = eventos;  // Asignar el objeto eventos
        setTitle("Calendario Personalizado");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        fechaActual = LocalDate.now();  // Obtener la fecha actual

        // Panel principal que contiene el calendario
        calendarioPanel = new JPanel();
        calendarioPanel.setLayout(new GridLayout(0, 7));  // 7 columnas para los días de la semana

        // Crear la cabecera con los días de la semana
        String[] dias = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        for (String dia : dias) {
            calendarioPanel.add(new JLabel(dia, SwingConstants.CENTER));
        }

        // Generar los días del mes
        generarCalendario();

        // Añadir el panel del calendario al marco
        add(calendarioPanel, BorderLayout.CENTER);
    }

    // Método para generar el calendario
    private void generarCalendario() {
        // Obtener el primer día del mes y el número total de días en el mes
        LocalDate primerDiaDelMes = fechaActual.withDayOfMonth(1);
        int diasEnElMes = fechaActual.lengthOfMonth();
        int primerDiaDeLaSemana = primerDiaDelMes.getDayOfWeek().getValue();  // 1 = Lunes, 7 = Domingo

        // Vaciar el panel antes de generar el calendario
        calendarioPanel.removeAll();

        // Reagregar los días de la semana
        String[] dias = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        for (String dia : dias) {
            calendarioPanel.add(new JLabel(dia, SwingConstants.CENTER));
        }

        // Agregar espacios vacíos antes del primer día del mes
        for (int i = 0; i < primerDiaDeLaSemana; i++) {
            calendarioPanel.add(new JLabel(""));
        }

        // Agregar los días del mes
        for (int i = 1; i <= diasEnElMes; i++) {
            LocalDate diaActual = fechaActual.withDayOfMonth(i);
            JButton botonDia = new JButton(String.valueOf(i));

            // Verificar si el día tiene eventos y agregar una marca
            List<Evento> eventosDelDia = eventos.getEventosEnFecha(java.sql.Date.valueOf(diaActual));
            if (!eventosDelDia.isEmpty()) {
                botonDia.setBackground(Color.YELLOW);  // Cambiar el color de fondo
                botonDia.setToolTipText("Hay eventos para este día");  // Tooltip opcional
            }

            botonDia.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Mostrar los eventos para el día seleccionado
                    StringBuilder eventosStr = new StringBuilder("Eventos del " + diaActual + ":\n");
                    for (Evento evento : eventosDelDia) {
                        eventosStr.append(evento.getDescripcion()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, eventosStr.toString());
                }
            });

            calendarioPanel.add(botonDia);
        }

        // Actualizar el layout para que se muestren los componentes
        calendarioPanel.revalidate();
        calendarioPanel.repaint();
    }
}

