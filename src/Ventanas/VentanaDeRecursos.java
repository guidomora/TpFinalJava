package Ventanas;

import Evento.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaDeRecursos extends JFrame {

    private List<Evento> listaEventos;  // Lista de eventos disponibles
    private JList<Evento> eventosList;  // JList para mostrar los eventos
    private JButton gestionarButton;  // Botón para gestionar recursos

    public VentanaDeRecursos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;

        setTitle("Seleccionar Evento para Gestionar Recursos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel para la lista de eventos
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear un JList para mostrar los eventos
        DefaultListModel<Evento> model = new DefaultListModel<>();
        for (Evento evento : listaEventos) {
            model.addElement(evento);  // Agregar eventos a la lista
        }
        eventosList = new JList<>(model);
        eventosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(eventosList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón para gestionar los recursos del evento seleccionado
        gestionarButton = new JButton("Gestionar Recursos");
        gestionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evento eventoSeleccionado = eventosList.getSelectedValue();
                if (eventoSeleccionado != null) {
                    // Si hay un evento seleccionado, abrir la ventana de recursos
                    new VentanaDeRecursosEditar(eventoSeleccionado).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un evento.");
                }
            }
        });

        panel.add(gestionarButton, BorderLayout.SOUTH);

        // Añadir el panel al frame
        add(panel);

        setVisible(true);
    }
}
