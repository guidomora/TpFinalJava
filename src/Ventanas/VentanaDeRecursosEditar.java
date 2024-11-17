package Ventanas;

import Evento.Evento;
import Recursos.Recursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDeRecursosEditar extends JFrame {

    private Evento evento;  // El evento al que se le van a gestionar los recursos
    private JCheckBox salonCheckBox;
    private JCheckBox audiovisualesCheckBox;
    private JCheckBox cateringCheckBox;

    public VentanaDeRecursosEditar(Evento evento) {
        this.evento = evento;

        setTitle("Gestionar Recursos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel para los recursos
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Crear los checkboxes para gestionar los recursos
        cateringCheckBox = new JCheckBox("Catering", evento.getRecursos().isCatering());
        audiovisualesCheckBox = new JCheckBox("Equipo audiovisual", evento.getRecursos().isEquipoAudiovisual());
        salonCheckBox = new JCheckBox("Salon", evento.getRecursos().isSalones());

        // Agregar los checkboxes al panel
        panel.add(salonCheckBox);
        panel.add(audiovisualesCheckBox);
        panel.add(cateringCheckBox);

        // Botón para guardar los cambios
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar los recursos del evento
                actualizarRecursos();
                JOptionPane.showMessageDialog(null, "Recursos actualizados correctamente.");
                dispose();
            }
        });

        panel.add(guardarButton);

        // Añadir el panel al frame
        add(panel);

        setVisible(true);
    }

    // Método para actualizar los recursos
    private void actualizarRecursos() {
        // Actualizar los recursos con los valores de los checkboxes
        evento.getRecursos().setCatering(cateringCheckBox.isSelected());
        evento.getRecursos().setEquipoAudiovisual(audiovisualesCheckBox.isSelected());
        evento.getRecursos().setSalones(salonCheckBox.isSelected());
    }
}
