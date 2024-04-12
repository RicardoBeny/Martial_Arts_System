package pt.ipleiria.estg.dei.ei.esoft.resultados;

import pt.ipleiria.estg.dei.ei.esoft.JanelaPrincipal;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Resultados extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel setaAtrasPanel;
    private JButton btnSetaAtras;
    private JPanel importarFicheiroPanel;
    private JButton btnMedalhacaoGeral;

    public Resultados(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnMedalhacaoGeral.addActionListener(this::btnMedalhacaoGeralActionPerformed);
    }

    public static void abrirPaginaResultados(){
        new Resultados("Resultados").setVisible(true);
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

    private void btnCalendarioActionPerformed(ActionEvent actionEvent) {
        CalendarioEventos.abrirPaginaCalendario();
        this.dispose();
    }

    private void btnMedalhacaoGeralActionPerformed(ActionEvent actionEvent) {
        MedalhacaoGeral.abrirPaginaMedalhacaoGeral();
        this.dispose();
    }
}
