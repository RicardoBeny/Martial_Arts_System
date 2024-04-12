package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class AtletasProva extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel setaAtras;
    private JButton btnSetaAtras;
    private JPanel importarFicheiroPanel;
    private JButton btnImportFile;
    private JFileChooser fileChooser;
    private int idEvento;
    public AtletasProva(String title, int idEvento) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        this.idEvento = idEvento;
        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnImportFile.addActionListener(this::btnImportarEventosActionPerformed);
    }

    public static void abrirPaginaAtletasProvas(int idEvento){
        new AtletasProva("Atletas Prova", idEvento).setVisible(true);
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        GestaoProvas.abrirPaginaGestaoProvas(idEvento);
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

    private void btnCalendarioActionPerformed(ActionEvent actionEvent) {
        CalendarioEventos.abrirPaginaCalendario();
        this.dispose();
    }

    private void btnImportarEventosActionPerformed(ActionEvent actionEvent) {
        fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".json","json");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        fileChooser.showSaveDialog(null);
    }
}
