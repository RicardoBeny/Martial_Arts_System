package pt.ipleiria.estg.dei.ei.esoft.calendario;

import pt.ipleiria.estg.dei.ei.esoft.JanelaPrincipal;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CalendarioEventos extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel setaAtrasPanel;
    private JButton btnSetaAtras;

    public CalendarioEventos(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();
        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);

    }

    public static void abrirPaginaCalendario(){
        new CalendarioEventos("Calendário").setVisible(true);
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        JanelaPrincipal.abrirJanelaPrincipal();
        this.dispose();
    }

    private void btnEventosActionPerformed(ActionEvent actionEvent) {
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }

    private void btnAtletasActionPerformed(ActionEvent actionEvent) {
        GestaoAtletas.abrirPaginaGestaoAtletas();
        this.dispose();
    }

    private void btnResultadosActionPerformed(ActionEvent actionEvent) {
        Resultados.abrirPaginaResultados();
        this.dispose();
    }

}
