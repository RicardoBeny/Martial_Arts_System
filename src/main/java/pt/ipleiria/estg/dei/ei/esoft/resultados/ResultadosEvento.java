package pt.ipleiria.estg.dei.ei.esoft.resultados;

import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ResultadosEvento extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel setaAtrasPanel;
    private JButton btnSetaAtras;
    private JPanel btnsPanel;
    private JButton btnMedalhas;
    private JLabel espaco;

    public ResultadosEvento(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnMedalhas.addActionListener(this::btnMedalhasActionPerformed);
    }

    public static void abrirPaginaResultadosEvento(){
        new ResultadosEvento("Resultados Evento").setVisible(true);
    }

    private void abrirPaginaResultados(){
        Resultados.abrirPaginaResultados();
        this.dispose();
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        abrirPaginaResultados();
    }

    private void btnMedalhasActionPerformed(ActionEvent actionEvent){
        MedalhacaoGeral.abrirPaginaMedalhacaoGeral();
        this.dispose();
    }

    private void btnEventosActionPerformed (ActionEvent actionEvent){
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }

    private void btnAtletasActionPerformed(ActionEvent actionEvent) {
        GestaoAtletas.abrirPaginaGestaoAtletas();
        this.dispose();
    }

    private void btnResultadosActionPerformed(ActionEvent actionEvent) {
        abrirPaginaResultados();
    }

    private void btnCalendarioActionPerformed(ActionEvent actionEvent) {
        CalendarioEventos.abrirPaginaCalendario();
        this.dispose();
    }
}
