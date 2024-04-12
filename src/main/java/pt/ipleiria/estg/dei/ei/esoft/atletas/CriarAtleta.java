package pt.ipleiria.estg.dei.ei.esoft.atletas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.ModeloTabelaEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriarAtleta extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel btnsPanel;
    private JButton btnCriar;
    private JButton btnCancelar;
    private JLabel espaco;
    private JPanel formPanel;
    private JPanel esquerdoPanel;
    private JLabel nome;
    private JTextField textNome;
    private JLabel local;
    private JTextField textPeso;
    private JLabel modalidade;
    private JTextField textModalidade;
    private JLabel genero;
    private JPanel generoPanel;
    private JPanel direitoPanel;
    private JLabel pais;
    private JComboBox<String> paisesComboBox;
    private JLabel dataNascimento;
    private JTextField textDataNascimento;
    private JLabel contacto;
    private JTextField textContacto;
    private JRadioButton radioBtnMasculino;
    private JRadioButton radioBtnFeminino;

    public CriarAtleta(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnCriar.addActionListener(this::btnCriarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        fillComboBoxPaises(paisesComboBox);
        paisesComboBox.setEditable(true);
        paisesComboBox.setSelectedItem("Selecione um país");

        configurarTxtModalidade();
    }

    public static void abrirPaginaCriarAtletas(){
        new CriarAtleta("Criar Atleta").setVisible(true);
    }

    private void configurarTxtModalidade() {
        textModalidade.setEditable(false);
        textModalidade.setText("Judo");
    }

    private void abrirPaginaAtletas(){
        GestaoAtletas.abrirPaginaGestaoAtletas();
        this.dispose();
    }
    private void btnCriarActionPerformed(ActionEvent actionEvent) {
            // TODO: CRIAR ATLETA

            if (validarNome() != 0){
                mostrarErro(validarNome());
                return;
            }

            if (validarPeso() != 0){
                mostrarErro(validarPeso());
                return;
            }

            if (validarGenero() != 0){
                mostrarErro(validarGenero());
                return;
            }

            if (validarPais() != 0){
                mostrarErro(validarPais());
                return;
            }

            if (validarData() != 0){
                mostrarErro(validarData());
                return;
            }

            if (validarContacto() != 0){
                mostrarErro(validarContacto());
                return;
            }


        JSONObject athleteData = new JSONObject();
        athleteData.put("nome", textNome.getText());
        athleteData.put("nacionalidade", paisesComboBox.getSelectedItem());
        athleteData.put("genero", radioBtnMasculino.isSelected() ? "Masculino" : "Feminino");
        athleteData.put("modalidade", "Judo");
        athleteData.put("peso", textPeso.getText());
        athleteData.put("dataNascimento", textDataNascimento.getText());
        athleteData.put("contacto", textContacto.getText());

        String escalaoEtario = calcularEscalaoEtario(textDataNascimento.getText());
        athleteData.put("escalaoEtario", escalaoEtario);

        String filePath = "src/main/java/pt/ipleiria/estg/dei/ei/esoft/atletas/atletasApp.json";

        writeDataToJsonFile(athleteData, filePath);

        abrirPaginaAtletas();
        JOptionPane.showMessageDialog(mainPanel, "Atleta adicionado com sucesso");
    }

    private void mostrarErro (int codigo){

        switch(codigo){
            case 1:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo nome");
                break;
            case 2:
                JOptionPane.showMessageDialog(mainPanel, "O campo nome possui mais do que 50 caracteres");
                break;
            case 3:
                JOptionPane.showMessageDialog(mainPanel, "O campo nome possui caracteres numéricos");
                break;
            case 4:
                JOptionPane.showMessageDialog(mainPanel, "Selecione um país");
                break;
            case 5:
                JOptionPane.showMessageDialog(mainPanel, "Selecione um género");
                break;
            case 6:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo peso");
                break;
            case 7:
                JOptionPane.showMessageDialog(mainPanel, "O campo peso possui um formato errado");
                break;
            case 8:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo data");
                break;
            case 9:
                JOptionPane.showMessageDialog(mainPanel, "Insira uma data válida");
                break;
            case 10:
                JOptionPane.showMessageDialog(mainPanel, "Atleta possui menos de 8 anos de idade");
                break;
            case 11:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo contacto");
                break;
            case 12:
                JOptionPane.showMessageDialog(mainPanel, "O campo contacto possui tem de possuir 9 caracteres");
                break;
            case 13:
                JOptionPane.showMessageDialog(mainPanel, "O campo contacto possui caracteres inválidos (a-z,A-Z, espaços em branco, caracteres especiais)");
                break;
            case 14:
                JOptionPane.showMessageDialog(mainPanel, "O campo contacto é um número negativo");
                break;
            case 15:
                JOptionPane.showMessageDialog(mainPanel, "O contacto do atleta já se encontra em uso");
                break;
            case 16:
                JOptionPane.showMessageDialog(mainPanel, "O campo nome possui caracteres especiais");
                break;
            case 17:
                JOptionPane.showMessageDialog(mainPanel, "Dois campos géneros selecionados");
                break;
        }
    }

    private void btnCancelarActionPerformed(ActionEvent actionEvent) {
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

    private int validarData(){

        String data = textDataNascimento.getText();

        if (data.isEmpty()){
            return 8;
        }

        Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher matcher = pattern.matcher(data);

        if (!matcher.matches()){
            return  9;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInserida = LocalDate.parse(data, formatter);

        LocalDate dataAtual = LocalDate.now();

        if (!dataInserida.isBefore(dataAtual)){
            return  9;
        }

        Period ageDifference = Period.between(dataInserida, dataAtual);

        if (ageDifference.getYears() < 8) {
            return 10; // or any other error code you prefer
        }

        return 0;
    }
    private int validarPeso() {

        String pesoIN = textPeso.getText();

        if (pesoIN.isEmpty()){
            return 6;
        }

        if (!Pattern.matches(".*\\d.*", pesoIN)) {
            return 7;
        }

        Float peso;
        try {
            peso = Float.parseFloat(pesoIN);
        } catch (NumberFormatException e) {
            return 7; // Invalid peso format
        }

        if (peso == null) {
            return 7; // Null peso value
        }

        String pesoString = String.valueOf(peso);
        int indexOfDecimalSeparator = pesoString.indexOf(".");

        if (indexOfDecimalSeparator != -1) {
            int decimalPlaces = pesoString.length() - indexOfDecimalSeparator - 1;
            if(decimalPlaces > 2){
                return 7;
            }
        }

        return 0;
    }

    private int validarGenero(){

        if (radioBtnMasculino.isSelected() && radioBtnFeminino.isSelected()){
            return 17;
        }

        if (!radioBtnMasculino.isSelected() && !radioBtnFeminino.isSelected()){
            return 5;
        }

        return 0;
    }

    private int validarPais(){

        String pais = (String) paisesComboBox.getSelectedItem();

        if (pais.equals("Selecione um país")){
            return 4;
        }

        return 0;
    }

    private int validarNome() {

        String nome = textNome.getText();

        if (nome.isEmpty()){
            return 1;
        }

        if (Pattern.matches(".*\\d.*", nome)) {
            return 3;
        }

        if (!Pattern.matches("^[a-zA-Z ç]+$", nome)){
            return 16;
        }

        if (nome.length() > 50){
            return 2;
        }

        return 0;
    }

    private int validarContacto() {
        String contacto = textContacto.getText();

        if (contacto.isEmpty()){
            return 11;
        }

        if (contacto.length() != 9){
            return 12;
        }

        if (!Pattern.matches(".*\\d.*", contacto)) {
            return 13;
        }

        int contactoInt;
        try {
            contactoInt = Integer.parseInt(contacto);
        } catch (NumberFormatException e) {
            return 13; // Invalid contacto format
        }

        if (contactoInt <= 0) {
            return 14; // Invalid contacto value
        }

        if (contactoExistsInFile(contacto)) {
            return 15; // Contacto already exists
        }

        return 0;
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
            JOptionPane.showMessageDialog(mainPanel, "Atleta criado com sucesso.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainPanel, "Não foi possivel criar o atleta no ficheiro json");
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

    private String calcularEscalaoEtario(String dataNascimento) {
        String[] parts = dataNascimento.split("/");
        int dia = Integer.parseInt(parts[0]);
        int mes = Integer.parseInt(parts[1]);
        int ano = Integer.parseInt(parts[2]);

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        int age = currentYear - ano;
        if (mes > currentMonth || (mes == currentMonth && dia > currentDay)) {
            age--;
        }

        if (age >= 8 && age <= 10) {
            return "Bejamins";
        } else if (age == 11) {
            return "Infantis";
        } else if (age == 12) {
            return "Iniciados";
        } else if (age >= 13 && age <= 14) {
            return "Juvenis";
        } else if (age >= 15 && age <= 17) {
            return "Cadetes";
        } else if (age >= 18 && age <= 20) {
            return "Juniores";
        } else if (age >= 21 && age <= 22) {
            return "Sub23";
        } else if (age >= 21) {
            return "Seniores";
        } else if (age >= 30) {
            return "Veteranos";
        }
        return "Varios";
    }

    private boolean contactoExistsInFile(String contacto) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/atletas/atletasApp.json")) {
            // Faz o parsing do arquivo JSON

            if (!reader.ready()){
                return false;
            }

            JSONArray jsonArray = (JSONArray) parser.parse(reader);


            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                String contactoJSON = (String) jsonObject.get("contacto");
                if (contacto.equals(contactoJSON)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
