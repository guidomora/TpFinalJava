package Ventanas;

import Eventos.Eventos;
import Evento.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaVerEventos extends JFrame {

    private Eventos eventos; 
    private JTextField txtFecha; 
    private JTextArea areaEventos; 
    public VentanaVerEventos(Eventos eventos) {
        this.eventos = eventos;

        setTitle("Ver Eventos");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBusqueda = new JPanel(new FlowLayout());
        JLabel lblFecha = new JLabel("Buscar por fecha (dd/mm/yy):");
        txtFecha = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(lblFecha);
        panelBusqueda.add(txtFecha);
        panelBusqueda.add(btnBuscar);

        areaEventos = new JTextArea();
        areaEventos.setEditable(false);
        JScrollPane scrollEventos = new JScrollPane(areaEventos);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollEventos, BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEventosPorFecha();
            }
        });

        mostrarEventos();
    }

    private void mostrarEventos() {
        areaEventos.setText(""); 
        for (Evento evento : eventos.getListaEventos()) {
            areaEventos.append(evento.getEvento() + "\n");
        }
    }

    private void buscarEventosPorFecha() {
        String fecha = txtFecha.getText().trim();
        areaEventos.setText(""); 
        for (Evento evento : eventos.getListaEventos()) {
            if (evento.getFecha().equals(fecha)) {
                areaEventos.append(evento.getEvento() + "\n");
            }
        }

        if (areaEventos.getText().isEmpty()) {
            areaEventos.setText("No se encontraron eventos para la fecha: " + fecha);
        }
    }
}
