package Ventanas;

import Evento.Evento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEditarEventos extends JFrame {
    
    private List<Evento> eventos; 
    private JComboBox<Evento> cbEventos; 
    private JTextField tfDescripcion;
    private JTextField tfFecha;
    private JTextField tfUbicacion;
    private JTextField tfCapacidad;
    private JComboBox<String> cbCategoria;
    private JButton btnGuardar;
    private JButton btnCancelar;

    // Constructor
    public VentanaEditarEventos(List<Evento> eventos) {
        this.eventos = eventos; 
        setTitle("Editar Evento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setLocationRelativeTo(null);  
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Seleccionar Evento:"));
        cbEventos = new JComboBox<>(eventos.toArray(new Evento[0]));
        cbEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosEvento((Evento) cbEventos.getSelectedItem());
            }
        });
        add(cbEventos);

        add(new JLabel("Descripción:"));
        tfDescripcion = new JTextField();
        add(tfDescripcion);

        add(new JLabel("Fecha:"));
        tfFecha = new JTextField();
        add(tfFecha);

        add(new JLabel("Ubicación:"));
        tfUbicacion = new JTextField();
        add(tfUbicacion);

        add(new JLabel("Capacidad:"));
        tfCapacidad = new JTextField();
        add(tfCapacidad);

        add(new JLabel("Categoría:"));
        String[] categorias = {"Pequeño", "Mediano", "Grande"};
        cbCategoria = new JComboBox<>(categorias);
        add(cbCategoria);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        add(btnGuardar);
        add(btnCancelar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });

        if (!eventos.isEmpty()) {
            cargarDatosEvento(eventos.get(0));  
        }
    }

    private void cargarDatosEvento(Evento evento) {
        tfDescripcion.setText(evento.getDescripcion());
        tfFecha.setText(evento.getFecha());
        tfUbicacion.setText(evento.getUbicacion());
        tfCapacidad.setText(String.valueOf(evento.getCapacidad()));
        cbCategoria.setSelectedItem(evento.getCategoria());
    }

    private void guardarCambios() {
        try {
            Evento eventoSeleccionado = (Evento) cbEventos.getSelectedItem();
            String descripcion = tfDescripcion.getText();
            String fecha = tfFecha.getText();
            String ubicacion = tfUbicacion.getText();
            int capacidad = Integer.parseInt(tfCapacidad.getText());
            String categoria = (String) cbCategoria.getSelectedItem();

            eventoSeleccionado.setCategoria(categoria);
            eventoSeleccionado.setDescripcion(descripcion);
            eventoSeleccionado.setFecha(fecha);
            eventoSeleccionado.setUbicacion(ubicacion);
            eventoSeleccionado.setCapacidad(capacidad);


            JOptionPane.showMessageDialog(this, "Evento actualizado con éxito");
            dispose();  
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa una capacidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
