import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Evento.Evento;
import Eventos.Eventos;
import Recursos.Recursos;
import Ventanas.VentanaCrearEventos;
import Ventanas.VentanaDeCalendario;
import Ventanas.VentanaDeRecursos;
import Ventanas.VentanaVerEventos;

public class App {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Gestión de Eventos");

        JButton crearEvento = new JButton("Crear Evento");
        JButton verEventos = new JButton("Ver Eventos");
        JButton asistentes = new JButton("Asistentes");
        JButton calendario = new JButton("Calendario de Eventos");
        JButton recursos = new JButton("Recursos de Eventos");
        JButton salir = new JButton("Salir");

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout());
        panelCentral.add(crearEvento);
        panelCentral.add(verEventos);
        panelCentral.add(asistentes);
        panelCentral.add(calendario);
        panelCentral.add(recursos);

        
        ventana.setLayout(new BorderLayout());
        ventana.add(panelCentral, BorderLayout.CENTER);
        ventana.add(salir, BorderLayout.SOUTH); 

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(900, 500);
        ventana.setVisible(true); 


        Eventos eventos = new Eventos();
        eventos.addEvento(new Evento("gym", "17/11/24", "Costanera", 300, new Recursos(true, false, false)));
        eventos.addEvento(new Evento("empresas", "24/11/24", "Costanera", 2300, new Recursos(true, true, true)));
        eventos.addEvento(new Evento("e-sports", "28/11/24", "Costanera", 1100,  new Recursos(false, true, false)));
        eventos.addEvento(new Evento("comicon", "28/05/24", "Costanera", 2100, new Recursos(true, true, true)));


        crearEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaCrearEventos(eventos).setVisible(true);
            }
        });

        verEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaVerEventos(eventos).setVisible(true);
            }
        });

        asistentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Asistentes");
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
            }
        });

        recursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de selección de evento para gestionar recursos
                new VentanaDeRecursos(eventos.getListaEventos()).setVisible(true);
            }
        });
        

        // calendario.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         new VentanaDeCalendario(eventos).setVisible(true);
        //     }
        // });
    }
}
