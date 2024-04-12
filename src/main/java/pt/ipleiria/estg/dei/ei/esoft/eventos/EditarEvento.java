package pt.ipleiria.estg.dei.ei.esoft.eventos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario.getValues;

public class EditarEvento extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel btnsPanel;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel espaco;
    private JPanel formPanel;
    private JPanel esquerdoPanel;
    private JLabel nome;
    private JTextField textNome;
    private JLabel local;
    private JTextField textLocal;
    private JLabel modalidade;
    private JTextField textModalidade;
    private JLabel escalaoEtario;
    private JComboBox dropdownEscalaoEtario;
    private JLabel genero;
    private JPanel generoPanel;
    private JCheckBox masculinoCheckBox;
    private JCheckBox femininoCheckBox;
    private JPanel direitoPanel;
    private JLabel pais;
    private JComboBox paisesComboBox;
    private JPanel categoriasPesoPanel;
    private JPanel textoPanel;
    private JLabel categoriasPeso;
    private JPanel masculinoPanel;
    private JLabel masculino;
    private JCheckBox CB38;
    private JCheckBox CB42;
    private JCheckBox CB46;
    private JCheckBox CB50;
    private JCheckBox CB55;
    private JCheckBox CB60;
    private JCheckBox CB66;
    private JCheckBox CB73;
    private JCheckBox CB81;
    private JCheckBox CB81M;
    private JCheckBox CB90;
    private JCheckBox CB90M;
    private JCheckBox CB100;
    private JCheckBox CB100M;
    private JPanel femininoPanel;
    private JCheckBox CB40;
    private JCheckBox CB44;
    private JCheckBox CB48;
    private JCheckBox CB52;
    private JCheckBox CB57;
    private JCheckBox CB63;
    private JCheckBox CB70;
    private JCheckBox CB70M;
    private JCheckBox CB78;
    private JCheckBox CB78M;
    private JPanel cimaPanel;
    private JPanel textDataInicioPanel;
    private JLabel dataInicio;
    private JLabel formataData;
    private JPanel textDataFinalPanel;
    private JLabel dataFinal;
    private JPanel baixoPanel;
    private JPanel infoDataInicioPanel;
    private JTextField textDataInicio;
    private JPanel infoDataFinalPanel;
    private JTextField textDataFinal;

    private final String[] escaloes = getValues(EscalaoEtario.class);

    private int idEvento;

    public EditarEvento(String title, int idEvento){
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
        btnConfirmar.addActionListener(this::btnEditarEventoActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarEventoActionPerformed);

        fillComboBoxPaises(paisesComboBox);
        paisesComboBox.setEditable(true);
        paisesComboBox.setSelectedItem("Selecione um país");

        fillComboBoxEscaloes(dropdownEscalaoEtario);
        dropdownEscalaoEtario.setEditable(true);

        mostrarEvento(idEvento);
    }

    public static void abrirPaginaEditarEvento (int idEvento){
        new EditarEvento("Editar Evento", idEvento).setVisible(true);
    }

    private void abrirPaginaEventos(){
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }

    public void btnEditarEventoActionPerformed(ActionEvent actionEvent) {
        // TODO: EDITAR EVENTO

        if (validarNome() != 0){
            mostrarErro(validarNome());
            return;
        }

        if (validarData(0) != 0){
            mostrarErro(validarData(0)); // data inicio
            return;
        }

        if (validarData(1) != 0){
            mostrarErro(validarData(1)); // data final
            return;
        }

        if (validarLocal() != 0){
            mostrarErro(validarLocal());
            return;
        }

        if (validarPais() != 0){
            mostrarErro(validarPais());
            return;
        }

        if (validarModalidade() != 0){
            // modalidade estatica judo
        }

        if (validarGeneroCategoriaPeso() != 0){
            mostrarErro(validarGeneroCategoriaPeso());
            return;
        }

        editarEventoJSON(idEvento);
        abrirPaginaEventos();
    }

    private void mostrarErro (int codigo){

        switch(codigo){
            case 1:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo nome");
                break;
            case 2:
                JOptionPane.showMessageDialog(mainPanel, "Nome preenchido com caracteres numéricos");
                break;
            case 3:
                JOptionPane.showMessageDialog(mainPanel, "Nome preenchido com caracteres especiais");
                break;
            case 4:
                JOptionPane.showMessageDialog(mainPanel, "Nome preenchido com mais de 50 caracteres");
                break;
            case 5:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo Data Inicio");
                break;
            case 6:
                JOptionPane.showMessageDialog(mainPanel, "Data Inicio preenchida com formato incorreto - dd/mm/aaaa");
                break;
            case 7:
                JOptionPane.showMessageDialog(mainPanel, "Data Inicio preenchida com caracteres inválidos - apenas permitido números");
                break;
            case 8:
                JOptionPane.showMessageDialog(mainPanel, "Data Inicio inválida - anterior à data atual");
                break;
            case 9:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo Data Final");
                break;
            case 10:
                JOptionPane.showMessageDialog(mainPanel, "Data Final preenchida com formato incorreto - dd/mm/aaaa");
                break;
            case 11:
                JOptionPane.showMessageDialog(mainPanel, "Data Final preenchida com caracteres inválidos - apenas permitido números");
                break;
            case 12:
                JOptionPane.showMessageDialog(mainPanel, "Data Final inválida - anterior à data atual ou anterior à Data Inicio");
                break;
            case 13:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo local");
                break;
            case 14:
                JOptionPane.showMessageDialog(mainPanel, "Local preenchido com caracteres inválidos - !?{}[]%&$#@()_");
                break;
            case 15:
                JOptionPane.showMessageDialog(mainPanel, "Local preenchido com mais de 70 caracteres");
                break;
            case 16:
                JOptionPane.showMessageDialog(mainPanel, "Selecione um país");
                break;
            case 20:
                JOptionPane.showMessageDialog(mainPanel, "Género e Categoria Peso não coincidem");
                break;
        }
    }

    private int validarNome() {

        String nome = textNome.getText();

        if (nome.isEmpty()){
            return 1;
        }

        if (Pattern.matches(".*\\d.*", nome)) {
            return 2;
        }

        if (!Pattern.matches("^[a-zA-Z ç]+$", nome)){
            return 3;
        }

        if (nome.length() > 50){
            return 4;
        }

        return 0;
    }

    private int validarData(int tipoData){

        String data, dataInicioCampo = null;

        if (tipoData == 0){
            data = textDataInicio.getText();
        }else{
            dataInicioCampo = textDataInicio.getText();
            data = textDataFinal.getText();
        }

        if (data.isEmpty()){
            return tipoData == 0 ? 5 : 9;
        }

        Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher matcher = pattern.matcher(data);

        if (!matcher.matches()){
            return tipoData == 0 ? 6 : 10;
        }

        Pattern pattern2 = Pattern.compile("[^0-9/]");
        Matcher matcher2 = pattern2.matcher(data);

        if (matcher2.find()){
            return tipoData == 0 ? 7 : 11;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInserida = LocalDate.parse(data, formatter);

        LocalDate dataAtual = LocalDate.now();

        if (dataInserida.isBefore(dataAtual)){
            return tipoData == 0 ? 8 : 12;
        }

        if (tipoData == 1){
            DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data1 = LocalDate.parse(dataInicioCampo, formatter3); // Converter a primeira data para LocalDate
            LocalDate data2 = LocalDate.parse(data, formatter3); // Converter a segunda data para LocalDate

            if (data1.isAfter(data2)) {
                return 12;
            }

        }

        return 0;
    }

    private int validarLocal(){

        String local = textLocal.getText();

        if (local.isEmpty()){
            return 13;
        }

        Pattern pattern = Pattern.compile("[!\\?{}\\[\\]%&$#@\\(\\)_]");
        Matcher matcher = pattern.matcher(local);

        if (matcher.find()){
            return 14;
        }

        if (local.length() > 70){
            return 15;
        }

        return 0;
    }

    private int validarPais(){

        String pais = (String) paisesComboBox.getSelectedItem();

        if (pais.equals("Selecione um país")){
            return 16;
        }

        return 0;
    }

    private int validarModalidade(){
        return 0;
    }

    private int validarGeneroCategoriaPeso(){

        if (masculinoCheckBox.isSelected() && !femininoCheckBox.isSelected()){
            if (CB40.isSelected() || CB44.isSelected() || CB48.isSelected() || CB52.isSelected() || CB57.isSelected() || CB63.isSelected() || CB70.isSelected() || CB70M.isSelected() || CB78.isSelected() || CB78M.isSelected()){
                return 20;
            }
        }

        if (!masculinoCheckBox.isSelected() && femininoCheckBox.isSelected()){
            if (CB38.isSelected() || CB42.isSelected() || CB46.isSelected() || CB50.isSelected() || CB55.isSelected() || CB60.isSelected() || CB66.isSelected() || CB73.isSelected() || CB81.isSelected() || CB81M.isSelected() || CB90.isSelected() || CB90M.isSelected() || CB100.isSelected() || CB100M.isSelected()){
                return 20;
            }
        }

        return 0;
    }

    public void btnCancelarEventoActionPerformed(ActionEvent actionEvent) {
        abrirPaginaEventos();
    }

    private void btnEventosActionPerformed(ActionEvent actionEvent) {
        abrirPaginaEventos();
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

    private void fillComboBoxPaises(JComboBox<String> comboBox){
        String[] paises = new String[Locale.getISOCountries().length];
        String[] codPais = Locale.getISOCountries();
        for (int i = 0; i < codPais.length; i++) {
            Locale obj = new Locale("", codPais[i]);
            paises[i] = obj.getDisplayCountry(Locale.forLanguageTag("pt-PT"));
        }

        Collator collator = Collator.getInstance(Locale.forLanguageTag("pt-PT"));
        List<String> paisesOrdenados = new ArrayList<>(List.of(paises));
        paisesOrdenados.sort(collator);

        for (String nacionalidade : paisesOrdenados) {
            comboBox.addItem(nacionalidade);
        }
    }

    private void fillComboBoxEscaloes(JComboBox<String> dropdownEscalaoEtario) {
        for (String escalao: escaloes){
            dropdownEscalaoEtario.addItem(escalao);
        }
    }

    private void mostrarEvento (int id){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonArray.get(id);

            textNome.setText((String) jsonObject.get("nome"));
            String data = (String) jsonObject.get("data");
            String[] datasSeparadas = data.split(";");
            textDataInicio.setText(datasSeparadas[0]);
            textDataFinal.setText(datasSeparadas[1]);
            textLocal.setText((String) jsonObject.get("local"));
            paisesComboBox.setSelectedItem(jsonObject.get("pais"));
            dropdownEscalaoEtario.setSelectedItem(jsonObject.get("escalaoEtario"));
            String genero = (String) jsonObject.get("genero");
            if (genero.contains("Masculino")){
                masculinoCheckBox.setSelected(true);
            }
            if (genero.contains("Feminino")){
                femininoCheckBox.setSelected(true);
            }

            textModalidade.setText((String) jsonObject.get("modalidade"));
            textModalidade.setEditable(false);

            String categoriasPeso = (String) jsonObject.get("categoriasPeso");
            if (categoriasPeso.contains("-40")){
                CB40.setSelected(true);
            }
            if(categoriasPeso.contains("-44")){
                CB44.setSelected(true);
            }
            if(categoriasPeso.contains("-48")){
                CB48.setSelected(true);
            }
            if(categoriasPeso.contains("-52")){
                CB52.setSelected(true);
            }
            if(categoriasPeso.contains("-57")){
                CB57.setSelected(true);
            }
            if(categoriasPeso.contains("-63")){
                CB63.setSelected(true);
            }
            if(categoriasPeso.contains("-70")){
                CB70.setSelected(true);
            }
            if(categoriasPeso.contains("+70")){
                CB70M.setSelected(true);
            }
            if(categoriasPeso.contains("-78")){
                CB78.setSelected(true);
            }
            if(categoriasPeso.contains("+78")){
                CB78M.setSelected(true);
            }

            if (categoriasPeso.contains("-38")){
                CB38.setSelected(true);
            }
            if(categoriasPeso.contains("-42")){
                CB42.setSelected(true);
            }
            if(categoriasPeso.contains("-46")){
                CB46.setSelected(true);
            }
            if(categoriasPeso.contains("-50")){
                CB50.setSelected(true);
            }
            if(categoriasPeso.contains("-55")){
                CB55.setSelected(true);
            }
            if(categoriasPeso.contains("-60")){
                CB60.setSelected(true);
            }
            if(categoriasPeso.contains("-66")){
                CB66.setSelected(true);
            }
            if(categoriasPeso.contains("-73")){
                CB73.setSelected(true);
            }
            if(categoriasPeso.contains("-81")){
                CB81.setSelected(true);
            }
            if(categoriasPeso.contains("+81")){
                CB81M.setSelected(true);
            }
            if(categoriasPeso.contains("-90")){
                CB90.setSelected(true);
            }
            if(categoriasPeso.contains("+90")){
                CB90M.setSelected(true);
            }
            if(categoriasPeso.contains("-100")){
                CB100.setSelected(true);
            }
            if(categoriasPeso.contains("+100")){
                CB100M.setSelected(true);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editarEventoJSON (int id){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonArray.get(id);

            jsonObject.put("nome", textNome.getText());
            jsonObject.put("data", textDataInicio.getText() + ";" + textDataFinal.getText());
            jsonObject.put("local", textLocal.getText());
            jsonObject.put("pais", paisesComboBox.getSelectedItem().toString());
            jsonObject.put("modalidade", textModalidade.getText());
            jsonObject.put("escalaoEtario",dropdownEscalaoEtario.getSelectedItem().toString());

            String categoriasPeso = "";
            if (CB40.isSelected()){
                categoriasPeso += "-40kg;";
            }
            if(CB44.isSelected()){
                categoriasPeso += "-44kg;";
            }
            if(CB48.isSelected()){
                categoriasPeso += "-48kg;";
            }
            if(CB52.isSelected()){
                categoriasPeso += "-52kg;";
            }
            if(CB57.isSelected()){
                categoriasPeso += "-57kg;";
            }
            if(CB63.isSelected()){
                categoriasPeso += "-63kg;";
            }
            if(CB70.isSelected()){
                categoriasPeso += "-70kg;";
            }
            if(CB70M.isSelected()){
                categoriasPeso += "+70kg;";
            }
            if(CB78.isSelected()){
                categoriasPeso += "-78kg;";
            }
            if(CB78M.isSelected()){
                categoriasPeso += "+78kg;";
            }

            if (CB38.isSelected()){
                categoriasPeso += "-38kg;";
            }
            if(CB42.isSelected()){
                categoriasPeso += "-42kg;";
            }
            if(CB46.isSelected()){
                categoriasPeso += "-46kg;";
            }
            if(CB50.isSelected()){
                categoriasPeso += "-50kg;";
            }
            if(CB55.isSelected()){
                categoriasPeso += "-55kg;";
            }
            if(CB60.isSelected()){
                categoriasPeso += "-60kg;";
            }
            if(CB66.isSelected()){
                categoriasPeso += "-66kg;";
            }
            if(CB73.isSelected()){
                categoriasPeso += "-73kg;";
            }
            if(CB81.isSelected()){
                categoriasPeso += "-81kg;";
            }
            if(CB81M.isSelected()){
                categoriasPeso += "+81kg;";
            }
            if(CB90.isSelected()){
                categoriasPeso += "-90kg;";
            }
            if(CB90M.isSelected()){
                categoriasPeso += "+90kg;";
            }
            if(CB100.isSelected()){
                categoriasPeso += "-100kg;";
            }
            if(CB100M.isSelected()){
                categoriasPeso += "+100kg;";
            }
            jsonObject.put("categoriasPeso",categoriasPeso);

            String genero = "";
            if (masculinoCheckBox.isSelected() && femininoCheckBox.isSelected()){
                genero = "Masculino;Feminino";
            }else if(masculinoCheckBox.isSelected()){
                genero = "Masculino;";
            }else if(femininoCheckBox.isSelected()){
                genero = "Feminino;";
            }
            jsonObject.put("genero",genero);

            if (id >= 0 && id < jsonArray.size()) {
                jsonArray.set(id, jsonObject);

                try (FileWriter fileWriter = new FileWriter("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonData = gson.toJson(jsonArray);

                    fileWriter.write(jsonData);
                    JOptionPane.showMessageDialog(mainPanel, "Evento editado com sucesso");
                } catch (IOException e) {
                    //System.out.println("Ocorreu um erro ao escrever o JSON atualizado no ficheiro: " + e.getMessage());
                    JOptionPane.showMessageDialog(mainPanel, "Não foi possivel editar o evento");
                }
            } else {
                //System.out.println("ID inválido. O objeto com o ID especificado não existe.");
                JOptionPane.showMessageDialog(mainPanel, "Não foi possivel editar o evento");
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            //System.out.println("Ocorreu um erro ao ler o arquivo JSON: " + e.getMessage());
            JOptionPane.showMessageDialog(mainPanel, "Não foi possivel editar o evento");
        }
    }
}
