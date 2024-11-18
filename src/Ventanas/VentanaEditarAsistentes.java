package Ventanas;

import Asistente.Asistente;
import Evento.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEditarAsistentes extends JFrame {
    private Evento evento;
    private DefaultListModel<String> asistentesModel;

    public VentanaEditarAsistentes(Evento evento) {
        this.evento = evento;

        setTitle("Gestionar Asistentes para: " + evento.getDescripcion());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Lista de asistentes
        asistentesModel = new DefaultListModel<>();
        JList<String> listaAsistentes = new JList<>(asistentesModel);
        cargarAsistentes();

        // Botón para agregar asistentes
        JButton botonAgregar = new JButton("Agregar Asistente");
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Nombre:");
                String apellido = JOptionPane.showInputDialog("Apellido:");
                String correo = JOptionPane.showInputDialog("Correo:");
                String telefono = JOptionPane.showInputDialog("Teléfono:");
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));

                Asistente nuevoAsistente = new Asistente(nombre, apellido, correo, telefono, edad);
                evento.getAsistentes().addAsistente(nuevoAsistente);

                // Actualizar lista
                cargarAsistentes();
            }
        });

        // Botón para eliminar asistentes
        JButton botonEliminar = new JButton("Eliminar Asistente");
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaAsistentes.getSelectedIndex();
                if (index != -1) {
                    evento.getAsistentes().getListaAsistentes().remove(index);
                    cargarAsistentes();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un asistente para eliminar.");
                }
            }
        });

        // Agregar componentes al panel
        panel.add(new JScrollPane(listaAsistentes), BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(botonAgregar);
        botonesPanel.add(botonEliminar);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void cargarAsistentes() {
        asistentesModel.clear();
        for (Asistente asistente : evento.getAsistentes().getListaAsistentes()) {
            asistentesModel.addElement(asistente.getAsistente());
        }
    }
    
}

