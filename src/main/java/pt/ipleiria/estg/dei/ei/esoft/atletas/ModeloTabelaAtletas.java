package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTabelaAtletas extends AbstractTableModel {

    private final String[] nomeColunas = {
            "Nome", "Data", "Contacto", "País", "Modalidade", "Género", "Escalão Etário", "Peso"
    };
    // TODO - IMPLEMENTAR PARA EVENTOS DINAMICAMENTE

    private final List<Atleta> atletas;

    ModeloTabelaAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }
    @Override
    public int getRowCount() {
        return atletas.size();
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
            case 0 -> atletas.get(rowIndex).getNome();
            // data
            case 1 -> atletas.get(rowIndex).getDataNascimento();
            // contacto
            case 2 -> atletas.get(rowIndex).getContacto();
            // pais
            case 3 -> atletas.get(rowIndex).getNacionalidade();
            // modalidade
            case 4 -> atletas.get(rowIndex).getModalidade();
            // genero
            case 5 -> atletas.get(rowIndex).getGenero();
            // escalao etario
            case 6 -> atletas.get(rowIndex).getEscalaoEtario();
            // peso
            case 7 -> atletas.get(rowIndex).getPeso();
            // numero errado coluna
            default -> throw new IllegalArgumentException("Valor 'Coluna' inválido");
        };
    }
}
