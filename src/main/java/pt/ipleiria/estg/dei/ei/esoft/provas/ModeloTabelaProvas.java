package pt.ipleiria.estg.dei.ei.esoft.provas;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTabelaProvas extends AbstractTableModel {

    private final String[] nomeColunas = {
            "Categoria Peso", "Género", "Escalão Etário", "Hora"
    };
    // TODO - IMPLEMENTAR PARA EVENTOS DINAMICAMENTE

    private final List<Prova> provas;

    ModeloTabelaProvas(List<Prova> provas) {
        this.provas = provas;
    }
    @Override
    public int getRowCount() {
        return provas.size();
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
          case 0 -> provas.get(rowIndex).getCategoriaPeso();
          case 1 -> provas.get(rowIndex).getGenero();
          case 2 -> provas.get(rowIndex).getEscalaoEtario();
          case 3 -> provas.get(rowIndex).getHora();
          default -> throw new IllegalArgumentException("Valor 'Coluna' inválido");
      };
    }
}