package Ventanas;

import Evento.Evento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEditarEventos extends JFrame {
    
    private List<Evento> eventos; // Lista de eventos
    private JComboBox<Evento> cbEventos; // Combo box para seleccionar evento
    private JTextField tfDescripcion;
    private JTextField tfFecha;
    private JTextField tfUbicacion;
    private JTextField tfCapacidad;
    private JComboBox<String> cbCategoria;
    private JButton btnGuardar;
    private JButton btnCancelar;

    // Constructor
    public VentanaEditarEventos(List<Evento> eventos) {
        this.eventos = eventos; // Recibimos la lista de eventos
        setTitle("Editar Evento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Cierra la ventana sin salir de la aplicación
        setLocationRelativeTo(null);  // Centra la ventana
        
        // Layout manager
        setLayout(new GridLayout(7, 2));

        // Etiqueta para seleccionar evento
        add(new JLabel("Seleccionar Evento:"));
        cbEventos = new JComboBox<>(eventos.toArray(new Evento[0]));
        cbEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosEvento((Evento) cbEventos.getSelectedItem());
            }
        });
        add(cbEventos);

        // Etiquetas y campos de texto
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

        // Botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        add(btnGuardar);
        add(btnCancelar);

        // Manejo de eventos de botones
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cerrar la ventana sin guardar
            }
        });

        // Cargar el primer evento al inicio
        if (!eventos.isEmpty()) {
            cargarDatosEvento(eventos.get(0));  // Cargar datos del primer evento
        }
    }

    // Cargar los datos del evento seleccionado en los campos de texto
    private void cargarDatosEvento(Evento evento) {
        tfDescripcion.setText(evento.getDescripcion());
        tfFecha.setText(evento.getFecha());
        tfUbicacion.setText(evento.getUbicacion());
        tfCapacidad.setText(String.valueOf(evento.getCapacidad()));
        cbCategoria.setSelectedItem(evento.getCategoria());
    }

    // Método para guardar los cambios
    private void guardarCambios() {
        try {
            // Obtener los valores editados
            Evento eventoSeleccionado = (Evento) cbEventos.getSelectedItem();
            String descripcion = tfDescripcion.getText();
            String fecha = tfFecha.getText();
            String ubicacion = tfUbicacion.getText();
            int capacidad = Integer.parseInt(tfCapacidad.getText());
            String categoria = (String) cbCategoria.getSelectedItem();

            // Actualizar el evento con los nuevos valores
            eventoSeleccionado.setCategoria(categoria);
            eventoSeleccionado.setDescripcion(descripcion);
            eventoSeleccionado.setFecha(fecha);
            eventoSeleccionado.setUbicacion(ubicacion);
            eventoSeleccionado.setCapacidad(capacidad);


            JOptionPane.showMessageDialog(this, "Evento actualizado con éxito");
            dispose();  // Cerrar la ventana después de guardar
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa una capacidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
