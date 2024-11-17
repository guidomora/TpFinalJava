package Ventanas;

import Eventos.Eventos;
import Evento.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaVerEventos extends JFrame {

    private Eventos eventos; // Objeto que contiene la lista de eventos
    private JTextField txtFecha; // Campo de texto para buscar por fecha
    private JTextArea areaEventos; // Área de texto para mostrar los eventos

    public VentanaVerEventos(Eventos eventos) {
        this.eventos = eventos;

        // Configuración de la ventana
        setTitle("Ver Eventos");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con el campo de búsqueda y botón
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        JLabel lblFecha = new JLabel("Buscar por fecha (dd/mm/yy):");
        txtFecha = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(lblFecha);
        panelBusqueda.add(txtFecha);
        panelBusqueda.add(btnBuscar);

        // Área de texto para mostrar los eventos
        areaEventos = new JTextArea();
        areaEventos.setEditable(false);
        JScrollPane scrollEventos = new JScrollPane(areaEventos);

        // Botón para cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        // Agregar componentes a la ventana
        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollEventos, BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);

        // Acción del botón de búsqueda
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEventosPorFecha();
            }
        });

        // Mostrar eventos iniciales (próximos)
        mostrarEventos();
    }

    private void mostrarEventos() {
        areaEventos.setText(""); // Limpiar el área de texto
        for (Evento evento : eventos.getListaEventos()) {
            areaEventos.append(evento.getEvento() + "\n");
        }
    }

    private void buscarEventosPorFecha() {
        String fecha = txtFecha.getText().trim();
        areaEventos.setText(""); // Limpiar el área de texto
        for (Evento evento : eventos.getListaEventos()) {
            if (evento.getFecha().equals(fecha)) {
                areaEventos.append(evento.getEvento() + "\n");
            }
        }

        // Si no se encuentra ningún evento
        if (areaEventos.getText().isEmpty()) {
            areaEventos.setText("No se encontraron eventos para la fecha: " + fecha);
        }
    }
}
