package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Evento.Evento;
import Eventos.Eventos;
import Recursos.Recursos;

public class VentanaCrearEventos extends JFrame {
    private JTextField descripcionField;
    private JTextField fechaField;
    private JTextField ubicacionField;
    private JTextField capacidadField;
    private JCheckBox salonesCheckBox;
    private JCheckBox equipoAudiovisualCheckBox;
    private JCheckBox cateringCheckBox;
    private JButton guardarButton;
    private Eventos eventos;
    public VentanaCrearEventos(Eventos eventos) {
        // Configuración básica de la ventana
        this.eventos = eventos;
        setTitle("Crear/Modificar Evento");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Cerrar ventana sin cerrar la aplicación
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre los componentes

        // Inicialización de los campos
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionField = new JTextField(20);

        JLabel fechaLabel = new JLabel("Fecha (dd/mm/aa):");
        fechaField = new JTextField(20);

        JLabel ubicacionLabel = new JLabel("Ubicación:");
        ubicacionField = new JTextField(20);

        JLabel capacidadLabel = new JLabel("Capacidad estimada:");
        capacidadField = new JTextField(20);

        // Casillas de verificación para los recursos
        JLabel recursosLabel = new JLabel("Recursos disponibles:");
        salonesCheckBox = new JCheckBox("Salones");
        equipoAudiovisualCheckBox = new JCheckBox("Equipo Audiovisual");
        cateringCheckBox = new JCheckBox("Catering");

        // Botón para guardar el evento
        guardarButton = new JButton("Guardar Evento");

        // Colocar los componentes en la ventana usando GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(descripcionLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(descripcionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(fechaLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(fechaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(ubicacionLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(ubicacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(capacidadLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(capacidadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(recursosLabel, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(salonesCheckBox, gbc);

        gbc.gridx = 1;
        add(equipoAudiovisualCheckBox, gbc);

        gbc.gridx = 2;
        add(cateringCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        add(guardarButton, gbc);

        // Acción del botón para guardar el evento
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos del formulario
                String descripcion = descripcionField.getText();
                String fecha = fechaField.getText();
                String ubicacion = ubicacionField.getText();
                int capacidad;

                try {
                    capacidad = Integer.parseInt(capacidadField.getText());

                    // Obtener los recursos seleccionados
                    boolean salones = salonesCheckBox.isSelected();
                    boolean equipoAudiovisual = equipoAudiovisualCheckBox.isSelected();
                    boolean catering = cateringCheckBox.isSelected();
                    
                    // Crear un objeto Recursos
                    Recursos recursos = new Recursos(salones, equipoAudiovisual, catering);

                    // Crear un nuevo evento con los recursos
                    Evento evento = new Evento(descripcion, fecha, ubicacion, capacidad, recursos);
                    eventos.addEvento(evento);  // Agregar evento a la lista de eventos

                    JOptionPane.showMessageDialog(null, "Evento guardado correctamente.");

                    // Limpiar campos después de guardar
                    descripcionField.setText("");
                    fechaField.setText("");
                    ubicacionField.setText("");
                    capacidadField.setText("");
                    salonesCheckBox.setSelected(false);
                    equipoAudiovisualCheckBox.setSelected(false);
                    cateringCheckBox.setSelected(false);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Capacidad debe ser un número válido.");
                }
            }
        });
    }
}
