package pt.ipleiria.estg.dei.ei.esoft.resultados;

import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MedalhacaoGeral extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel setaAtrasPanel;
    private JButton btnSetaAtras;

    public MedalhacaoGeral(String title){
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
    }

    public static void abrirPaginaMedalhacaoGeral(){
        new MedalhacaoGeral("Medalhação Geral").setVisible(true);
    }

    private void abrirPaginaResultados(){
        Resultados.abrirPaginaResultados();
        this.dispose();
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        abrirPaginaResultados();
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
