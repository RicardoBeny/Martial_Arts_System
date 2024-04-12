package pt.ipleiria.estg.dei.ei.esoft.eventos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTabelaEventos extends AbstractTableModel {

    private final String[] nomeColunas = {
            "Nome", "Data", "Local", "País", "Modalidade", "Género", "Escalão Etário", "Categoria Peso"
    };
    // TODO - IMPLEMENTAR PARA EVENTOS DINAMICAMENTE

    private final List<Evento> eventos;

    ModeloTabelaEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    @Override
    public int getRowCount() {
        //return eventos.size();
        return eventos.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex){
            // nome
            case 0 -> eventos.get(rowIndex).getNome();
            // data
            case 1 -> eventos.get(rowIndex).getDuracaoString();
            // local
            case 2 -> eventos.get(rowIndex).getLocal();
            // pais
            case 3 -> eventos.get(rowIndex).getPais();
            // modalidade
            case 4 -> eventos.get(rowIndex).getModalidade();
            // genero
            case 5 -> eventos.get(rowIndex).getGenero();
            // escalao etario
            case 6 -> eventos.get(rowIndex).getEscalaoEtario();
            // categorias peso
            case 7 -> eventos.get(rowIndex).getCategoriasPeso();
            // numero errado coluna
            default -> throw new IllegalArgumentException("Valor 'Coluna' inválido");
        };
    }
}
