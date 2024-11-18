package Ventanas;

import Evento.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaAsistentes extends JFrame {
    private List<Evento> eventos;

    public VentanaAsistentes(List<Evento> eventos) {
        this.eventos = eventos;

        setTitle("Seleccionar Evento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con lista de eventos
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> listaEventos = new JList<>(listModel);

        // Agregar eventos a la lista
        for (Evento evento : eventos) {
            listModel.addElement(evento.getDescripcion());
        }

        // Botón para gestionar asistentes del evento seleccionado
        JButton botonSeleccionar = new JButton("Gestionar Asistentes");
        botonSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaEventos.getSelectedIndex();
                if (index != -1) {
                    Evento eventoSeleccionado = eventos.get(index);
                    new VentanaEditarAsistentes(eventoSeleccionado).setVisible(true);
                    dispose(); // Cerrar esta ventana
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un evento.");
                }
            }
        });

        panel.add(new JScrollPane(listaEventos), BorderLayout.CENTER);
        panel.add(botonSeleccionar, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
