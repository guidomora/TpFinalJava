package Ventanas;

import Evento.Evento;
import Recursos.Recursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDeRecursosEditar extends JFrame {

    private Evento evento;  
    private JCheckBox salonCheckBox;
    private JCheckBox audiovisualesCheckBox;
    private JCheckBox cateringCheckBox;

    public VentanaDeRecursosEditar(Evento evento) {
        this.evento = evento;

        setTitle("Gestionar Recursos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        cateringCheckBox = new JCheckBox("Catering", evento.getRecursos().isCatering());
        audiovisualesCheckBox = new JCheckBox("Equipo audiovisual", evento.getRecursos().isEquipoAudiovisual());
        salonCheckBox = new JCheckBox("Salon", evento.getRecursos().isSalones());

        panel.add(salonCheckBox);
        panel.add(audiovisualesCheckBox);
        panel.add(cateringCheckBox);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarRecursos();
                JOptionPane.showMessageDialog(null, "Recursos actualizados correctamente.");
                dispose();
            }
        });

        panel.add(guardarButton);

        add(panel);

        setVisible(true);
    }

    private void actualizarRecursos() {
        evento.getRecursos().setCatering(cateringCheckBox.isSelected());
        evento.getRecursos().setEquipoAudiovisual(audiovisualesCheckBox.isSelected());
        evento.getRecursos().setSalones(salonCheckBox.isSelected());
    }
}
