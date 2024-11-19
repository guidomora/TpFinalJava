package Ventanas;

import Evento.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaDeRecursos extends JFrame {

    private List<Evento> listaEventos;
    private JList<Evento> eventosList;
    private JButton gestionarButton;  

    public VentanaDeRecursos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;

        setTitle("Seleccionar Evento para Gestionar Recursos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultListModel<Evento> model = new DefaultListModel<>();
        for (Evento evento : listaEventos) {
            model.addElement(evento);  
        }
        eventosList = new JList<>(model);
        eventosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(eventosList);
        panel.add(scrollPane, BorderLayout.CENTER);

        gestionarButton = new JButton("Gestionar Recursos");
        gestionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evento eventoSeleccionado = eventosList.getSelectedValue();
                if (eventoSeleccionado != null) {
                    new VentanaDeRecursosEditar(eventoSeleccionado).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un evento.");
                }
            }
        });

        panel.add(gestionarButton, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }
}
