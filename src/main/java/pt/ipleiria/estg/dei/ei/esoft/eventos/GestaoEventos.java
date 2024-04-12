package pt.ipleiria.estg.dei.ei.esoft.eventos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import pt.ipleiria.estg.dei.ei.esoft.*;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.provas.GestaoProvas;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Date;

public class GestaoEventos extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel adicionarEventoPanel;
    private JButton btnCriarEvento;
    private JButton btnSetaAtras;
    private JButton espaco;
    private JButton btnImportFile;
    private JPanel importarFicheiroPanel;
    private JPanel tablePanel;
    private JScrollPane tableScrollPlane;
    private JButton espacoDir;
    private JTable eventosTable;
    private JButton espacoTopo;
    private JButton espacoEsq;
    private JButton espacoBaixo;
    private JFileChooser fileChooser;
    public GestaoEventos(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnCriarEvento.addActionListener(this::btnCriarEventoActionPerformed);
        btnImportFile.addActionListener(this::btnImportarEventosActionPerformed);

        mostrarEventos();

    }

    public static void abrirPaginaGestaoEventos(){
        new GestaoEventos("Gestão Eventos").setVisible(true);
    }

    private void configurarScrollPlane(JScrollPane plane) {
        plane.getViewport().setBackground(new Color(23,23,23));
        plane.setBorder(null);
    }

    private void mostrarEventos(){
        configurarScrollPlane(tableScrollPlane);
        configurartabelaEventos(eventosTable);
    }

    private void configurartabelaEventos (JTable tabela){

        // TODO - PASSAR LISTA EVENTOS PARA MODELO DA TABELA
        // TODO - LER FICHEIRO JSON E COLOCAR EVENTOS EM LISTA

        ModeloTabelaEventos modeloTabelaEventos = popuplarTabelaEventos();
        if (modeloTabelaEventos != null) {
            tabela.setModel(modeloTabelaEventos);
        }
        tabela.setAutoCreateRowSorter(true);
        tabela.getTableHeader().setOpaque(false);

        //header
        JTableHeader header= tabela.getTableHeader();
        header.setBackground(new Color(37,37,37));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Inter",Font.BOLD,18));
        Border border = BorderFactory.createLineBorder(new Color(23,23,23));
        header.setBorder(border);
        UIManager.getDefaults().put("TableHeader.cellBorder", border);
        //desabilitar mexer colunas e tamanho
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);
        // alinhar meio
        ((DefaultTableCellRenderer)tabela.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        //popup quando cell cliacada
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItemEditar = new JMenuItem("Editar Evento");
        JMenuItem menuItemEliminar = new JMenuItem("Eliminar Evento");
        JMenuItem menuItemProvas = new JMenuItem("Ver Provas");

        mudarCorItemPopupMenu(menuItemEditar);
        mudarCorItemPopupMenu(menuItemEliminar);
        mudarCorItemPopupMenu(menuItemProvas);

        popupMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        popupMenu.add(menuItemEditar);
        popupMenu.add(menuItemEliminar);
        popupMenu.add(menuItemProvas);

        // action listener para items menu

        menuItemEditar.addActionListener(this::menuItemEditarActionPerformed);
        menuItemEliminar.addActionListener(this::menuItemEliminarActionPerformed);
        menuItemProvas.addActionListener(this::menuItemProvasActionPerformed);

        //adicionar popup menu tabela

        tabela.setComponentPopupMenu(popupMenu);
        tabela.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point point = e.getPoint();
                    int currentRow = tabela.rowAtPoint(point);
                    tabela.setRowSelectionInterval(currentRow, currentRow);
                }
            }
        });

    }

    private ModeloTabelaEventos popuplarTabelaEventos(){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON

            if (!reader.ready()){
                tablePanel.setVisible(false);
                return null;
            }else{
                tablePanel.setVisible(true);
            }

            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            // Cria uma lista de eventos
            List<Evento> eventos = new ArrayList<>();

            // Itera sobre o array JSON e cria objetos Evento
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // Extrai os valores do objeto JSON
                String nome = (String) jsonObject.get("nome");
                String data = (String) jsonObject.get("data");
                String[] datasSeparadas = data.split(";");
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                Date dataInicio = formatador.parse(datasSeparadas[0]);
                Date dataFinal = formatador.parse(datasSeparadas[1]);

                String local = (String) jsonObject.get("local");
                String pais = (String) jsonObject.get("pais");
                String modalidade = (String) jsonObject.get("modalidade");

                String generos = (String) jsonObject.get("genero");
                List<Genero> listaGenero = new ArrayList<>();
                if (!generos.isEmpty()){
                    String[] dadosSeparados = generos.split(";");

                    for (String valor : dadosSeparados) {
                        Genero genero = Genero.valueOf(valor);
                        listaGenero.add(genero);
                    }
                }


                String escalaoEtarioString = (String) jsonObject.get("escalaoEtario");
                EscalaoEtario escalaoEtario = EscalaoEtario.valueOf(escalaoEtarioString);

                String categoriasPeso = (String) jsonObject.get("categoriasPeso");
                String[] categoriaSeparadas = categoriasPeso.split(";");
                List<String> listaCategoriasPeso = Arrays.asList(categoriaSeparadas);

                // Cria um objeto Evento e adiciona à lista
                Evento evento = new Evento(nome, dataInicio, dataFinal, pais, local, escalaoEtario, listaCategoriasPeso, listaGenero, modalidade);
                eventos.add(evento);
            }

            // Cria o modelo da tabela com a lista de eventos

            return new ModeloTabelaEventos(eventos);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void menuItemEditarActionPerformed (ActionEvent actionEvent){
        // TODO - PREENCHER VALORES COM OS DO EVENTO
        EditarEvento.abrirPaginaEditarEvento(getLinha(actionEvent));
        this.dispose();
    }

    private void menuItemEliminarActionPerformed (ActionEvent actionEvent){
        // TODO - ELIMINAR EVENTO
        int row = getLinha(actionEvent);

        if (verificarAtletasEvento(row)){
            cancelarEventoJSON(row);
        }else{
            eliminarEventoJSON(row);
        }

        mostrarEventos();
    }

    private void menuItemProvasActionPerformed (ActionEvent actionEvent){
        // TODO - Abrir Provas do Evento
        GestaoProvas.abrirPaginaGestaoProvas(getLinha(actionEvent));
        this.dispose();
    }

    private int getLinha(ActionEvent actionEvent){
        JMenuItem menuItem = (JMenuItem) actionEvent.getSource();
        JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
        JTable tabela = (JTable) popupMenu.getInvoker();
        int row = tabela.getSelectedRow();
        return row;
    }

    private void mudarCorItemPopupMenu (JMenuItem item){
        item.setBackground(new Color(37,37,37));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 14));
    }

    public void btnCriarEventoActionPerformed(ActionEvent actionEvent) {
        CriarEvento.abrirPaginaCriarEvento();
        this.dispose();
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        JanelaPrincipal.abrirJanelaPrincipal();
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

    private void eliminarEventoJSON(int id){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            if (id >= 0 && id < jsonArray.size()) {

                jsonArray.remove(id);

                try (FileWriter fileWriter = new FileWriter("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
                    if (jsonArray.isEmpty()) {
                        // Se o JSONArray estiver vazio após a remoção, escreve um JSON vazio no arquivo
                        fileWriter.write("");
                    } else {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String jsonData = gson.toJson(jsonArray);
                        fileWriter.write(jsonData);
                    }
                    JOptionPane.showMessageDialog(mainPanel, "Evento eliminado com sucesso");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(mainPanel, "Não foi possivel eliminar o evento");
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Não foi possivel editar o evento");
            }

        } catch (IOException | org.json.simple.parser.ParseException e) {
            JOptionPane.showMessageDialog(mainPanel, "Não foi possivel eliminar o evento");
        }
    }

    private void cancelarEventoJSON(int id){
    }

    private boolean verificarAtletasEvento (int id){
        // do modulo 2 nao foi implementado a inscrição dos atletas entao apenas se elimina eventos nao se cancelam
        return false;
    }

    public void btnImportarEventosActionPerformed(ActionEvent actionEvent){
        fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheiros JSON","json");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileHidingEnabled(false);
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();

            if (!escreverFicheiroJSON(file)){
                //TODO - POPUP MENSAGEM ERRO
                return;
            }
            mostrarEventos();
        }
    }

    private boolean escreverFicheiroJSON(java.io.File file) {

        int counter = 0, write = 0;

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {

            if (!reader.ready()){
                return false;
            }

            Object json = parser.parse(reader);

            if (json instanceof JSONArray jsonArray) {

                JSONParser parser3 = new JSONParser();

                try (FileReader reader3 = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {

                    if (!reader3.ready()){
                        for (Object obj : jsonArray) {

                            JSONObject jsonObject = (JSONObject) obj;

                            if (!(jsonObject.containsKey("nome") && jsonObject.containsKey("local") && jsonObject.containsKey("pais") &&
                                    jsonObject.containsKey("modalidade") && jsonObject.containsKey("genero") && jsonObject.containsKey("data") &&
                                    jsonObject.containsKey("escalaoEtario") && jsonObject.containsKey("provas") && jsonObject.containsKey("categoriasPeso"))){
                                continue;
                            }

                            String filePath = "src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json";
                            writeDataToJsonFile(jsonObject, filePath);
                            counter += 1;
                        }
                        if (counter != 0){
                            JOptionPane.showMessageDialog(mainPanel, "Evento(s) importado(s)");
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(mainPanel, "Formato incorreto");
                            return false;
                        }

                    }

                    JSONArray jsonArray3 = (JSONArray) parser3.parse(reader3);

                    for (Object obj : jsonArray) {

                        JSONObject jsonObject = (JSONObject) obj;

                        if (!(jsonObject.containsKey("nome") && jsonObject.containsKey("local") && jsonObject.containsKey("pais") &&
                                jsonObject.containsKey("modalidade") && jsonObject.containsKey("genero") && jsonObject.containsKey("data") &&
                                jsonObject.containsKey("escalaoEtario") && jsonObject.containsKey("provas") && jsonObject.containsKey("categoriasPeso"))){
                            continue;
                        }

                        if (jsonArray3.contains(jsonObject)){
                            counter += 1;
                            continue;
                        }

                        write += 1;
                        String filePath = "src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json";
                        writeDataToJsonFile(jsonObject, filePath);
                    }

                    if (counter == jsonArray.size()){
                        JOptionPane.showMessageDialog(mainPanel, "Eventos já existentes");
                        return false;
                    }

                    if (write == 0){
                        JOptionPane.showMessageDialog(mainPanel, "Formato incorreto");
                        return false;
                    }else{
                        JOptionPane.showMessageDialog(mainPanel, "Evento(s) importado(s)");
                        return true;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if (json instanceof JSONObject jsonObject){

                JSONParser parser3 = new JSONParser();

                if (!(jsonObject.containsKey("nome") && jsonObject.containsKey("local") && jsonObject.containsKey("pais") &&
                        jsonObject.containsKey("modalidade") && jsonObject.containsKey("genero") && jsonObject.containsKey("data") &&
                        jsonObject.containsKey("escalaoEtario") && jsonObject.containsKey("provas") && jsonObject.containsKey("categoriasPeso"))){
                    JOptionPane.showMessageDialog(mainPanel, "Formato incorreto");
                    return false;
                }

                try (FileReader reader3 = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {

                    if (!reader3.ready()){
                        String filePath = "src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json";
                        writeDataToJsonFile(jsonObject, filePath);
                        JOptionPane.showMessageDialog(mainPanel, "Evento(s) importado(s)");
                        return true;
                    }

                    JSONArray jsonArray3 = (JSONArray) parser3.parse(reader3);

                    if (jsonArray3.contains(jsonObject)){
                        JOptionPane.showMessageDialog(mainPanel, "Evento já existente");
                        return false;
                    }

                    String filePath = "src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json";
                    writeDataToJsonFile(jsonObject, filePath);
                    JOptionPane.showMessageDialog(mainPanel, "Evento(s) importado(s)");
                    return true;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }else{
                return false;
            }

        } catch (IOException | ParseException e) {
            return false;
        }

        JOptionPane.showMessageDialog(mainPanel, "Evento(s) importado(s)");
        return true;
    }

    private void writeDataToJsonFile(JSONObject data, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonData = gson.toJson(data);

            boolean isEmpty = fileIsEmpty(filePath);

            if (!isEmpty) {
                removeLastCharacter(filePath);
                appendCharacter(filePath, ",");
                fileWriter.write(jsonData + "\n]");
            }else{
                fileWriter.write("[" + jsonData + "\n]");
            }

        } catch (IOException e) {

        }
    }

    private boolean fileIsEmpty(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine() == null;
        }
    }

    private void removeLastCharacter(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        long length = file.length();
        file.setLength(length - 1);
        file.close();
    }

    private void appendCharacter(String filePath, String character) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(character);
        }
    }
}
