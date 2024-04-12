package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AssociarProva extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel btnsPanel;
    private JButton btnCancelar;
    private JLabel espaco;
    private JButton btnImportFile;

    public AssociarProva(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        btnImportFile.addActionListener(this::btnImportarInscricoesActionPerformed);

    }

    public static void abrirPaginaAssociarAtletaProva (){
        new AssociarProva("Associar Prova").setVisible(true);
    }

    private void abrirPaginaAtletas(){
        GestaoAtletas.abrirPaginaGestaoAtletas();
        this.dispose();
    }

    private void btnCancelarActionPerformed (ActionEvent actionEvent){
        abrirPaginaAtletas();
    }

    private void btnEventosActionPerformed(ActionEvent actionEvent) {
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }

    private void btnAtletasActionPerformed(ActionEvent actionEvent) {
        abrirPaginaAtletas();
    }

    private void btnResultadosActionPerformed(ActionEvent actionEvent) {
        Resultados.abrirPaginaResultados();
        this.dispose();
    }

    private void btnCalendarioActionPerformed(ActionEvent actionEvent) {
        CalendarioEventos.abrirPaginaCalendario();
        this.dispose();
    }

    private void btnImportarInscricoesActionPerformed(ActionEvent actionEvent) {
    // TODO - Importar inscrições
    }
}
