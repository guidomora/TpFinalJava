import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import Asistente.Asistente;
import Asistentes.Asistentes;
import Evento.Evento;
import Eventos.Eventos;
import Recursos.Recursos;
import Ventanas.VentanaAsistentes;
import Ventanas.VentanaCrearEventos;
import Ventanas.VentanaDeCalendario;
import Ventanas.VentanaDeRecursos;
import Ventanas.VentanaEditarEventos;
import Ventanas.VentanaVerEventos;

public class App {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Gestión de Eventos");

        JButton crearEvento = new JButton("Crear Evento");
        JButton verEventos = new JButton("Ver Eventos");
        JButton asistentes = new JButton("Cargar Asistentes");
        JButton calendario = new JButton("Calendario de Eventos");
        JButton recursos = new JButton("Recursos de Eventos");
        JButton editarEvento = new JButton("Editar Evento");
        JButton salir = new JButton("Salir");

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout());
        panelCentral.add(crearEvento);
        panelCentral.add(verEventos);
        panelCentral.add(asistentes);
        panelCentral.add(calendario);
        panelCentral.add(recursos);
        panelCentral.add(editarEvento);

        ventana.setLayout(new BorderLayout());
        ventana.add(panelCentral, BorderLayout.CENTER);
        ventana.add(salir, BorderLayout.SOUTH);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(900, 500);
        ventana.setVisible(true);

        Asistente asistente1 = new Asistente("Guido", "Morabito", "guido@mail.com", "123456775", 24);
        Asistente asistente2 = new Asistente("Juan", "Perez", "juan@mail.com", "123456775", 30);
        Asistente asistente3 = new Asistente("Pedro", "Gomez", "pedro@mail", "123456775", 41);
        Asistente asistente4 = new Asistente("Maria", "Gonzalez", "maria@mail.com", "123456775", 19);

        // Creamos una lista de asistentes
        Asistentes asistentesLista = new Asistentes(List.of(asistente1, asistente2, asistente3, asistente4));

        // Creamos los eventos
        Eventos eventos = new Eventos();
        Evento evento1 = new Evento("gym", "17/11/24", "Costanera", 300, new Recursos(true, false, false));
        Evento evento2 = new Evento("empresas", "24/11/24", "Costanera", 2300, new Recursos(true, true, true));
        Evento evento3 = new Evento("e-sports", "28/11/24", "Costanera", 1100, new Recursos(false, true, false));
        Evento evento4 = new Evento("comicon", "28/05/24", "Costanera", 2100, new Recursos(true, true, true));

        // Agregar eventos a la lista
        eventos.addEvento(evento1);
        eventos.addEvento(evento2);
        eventos.addEvento(evento3);
        eventos.addEvento(evento4);

        // Ahora, añadimos los asistentes al evento
        evento1.addAsistente(asistente1);
        evento1.addAsistente(asistente2);
        evento2.addAsistente(asistente3);
        evento3.addAsistente(asistente4);

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
                new VentanaAsistentes(eventos.getListaEventos()).setVisible(true);
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

        editarEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaEditarEventos(eventos.getListaEventos()).setVisible(true);
            }
        });

    }
}
