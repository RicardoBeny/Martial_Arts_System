package pt.ipleiria.estg.dei.ei.esoft.provas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CriarProva extends JFrame{
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
    private JPanel esqPanel;
    private JPanel meioPanel;
    private JPanel dirPanel;
    private JPanel categoriasPesoPanel;
    private JPanel textoPanel;
    private JLabel categoriasPeso;
    private JPanel masculinoPanel;
    private JLabel masculino;
    private JRadioButton CB38;
    private JRadioButton CB42;
    private JRadioButton CB46;
    private JRadioButton CB50;
    private JRadioButton CB55;
    private JRadioButton CB60;
    private JRadioButton CB66;
    private JRadioButton CB73;
    private JRadioButton CB81;
    private JRadioButton CB81M;
    private JRadioButton CB90;
    private JRadioButton CB90M;
    private JRadioButton CB100;
    private JRadioButton CB100M;
    private JPanel femininoPanel;
    private JRadioButton CB40;
    private JRadioButton CB44;
    private JRadioButton CB48;
    private JRadioButton CB52;
    private JRadioButton CB57;
    private JRadioButton CB63;
    private JRadioButton CB70;
    private JRadioButton CB70M;
    private JRadioButton CB78;
    private JRadioButton CB78M;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JPanel radioBtnPanel;
    private JLabel genero;
    private JLabel escalaoEtario;
    private JPanel dropdownPanel;
    private JComboBox<String> dropdownEscalaoEtario;
    private JButton btnReset;

    private ButtonGroup btnGroupM, btnGroupF;
    private final String[] escaloes = {"Bejamins", "Infantis", "Iniciados", "Juvenis", "Cadetes", "Juniores", "Sub23", "Seniores","Veteranos"};

    private int idEvento;

    public CriarProva(String title, int idEvento){
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

        btnCriar.addActionListener(this::btnCriarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        btnReset.addActionListener(this::btnResetCampos);

        ButtonGroup groupGenero = new ButtonGroup();
        groupGenero.add(masculinoRadioButton);
        groupGenero.add(femininoRadioButton);

        btnGroupM = grupoCategoriasPesoMasculino();
        btnGroupF = grupoCategoriasPesoFeminino();

        fillComboBoxEscaloes(dropdownEscalaoEtario);
        dropdownEscalaoEtario.setEditable(true);

        configurarCampos();

    }

    public static void abrirPaginaCriarProva (int idEvento){
        new CriarProva("Criar Prova", idEvento).setVisible(true);
    }

    private void abrirPaginaProvas() {
        GestaoProvas.abrirPaginaGestaoProvas(idEvento);
        this.dispose();
    }

    private void configurarCampos(){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject evento = (JSONObject) jsonArray.get(idEvento);

            String genero = (String) evento.get("genero");

            if (genero.contains("Masculino") && !genero.contains("Feminino")){
                masculinoRadioButton.setSelected(true);
                femininoRadioButton.setEnabled(false);
            }else if (!genero.contains("Masculino") && genero.contains("Feminino")){
                femininoRadioButton.setSelected(true);
                masculinoRadioButton.setEnabled(false);
            }

            String escalaoEtario = (String) evento.get("escalaoEtario");

            if (!escalaoEtario.contains("Varios")){
                dropdownEscalaoEtario.setSelectedItem(escalaoEtario);
                dropdownEscalaoEtario.setEnabled(false);
            }

            String categoriaPeso = (String) evento.get("categoriasPeso");

            if (!categoriaPeso.isEmpty()){
                if (!categoriaPeso.contains("-40")){
                    CB40.setEnabled(false);
                }
                if(!categoriaPeso.contains("-44")){
                    CB44.setEnabled(false);
                }
                if(!categoriaPeso.contains("-48")){
                    CB48.setEnabled(false);
                }
                if(!categoriaPeso.contains("-52")){
                    CB52.setEnabled(false);
                }
                if(!categoriaPeso.contains("-57")){
                    CB57.setEnabled(false);
                }
                if(!categoriaPeso.contains("-63")){
                    CB63.setEnabled(false);
                }
                if(!categoriaPeso.contains("-70")){
                    CB70.setEnabled(false);
                }
                if(!categoriaPeso.contains("+70")){
                    CB70M.setEnabled(false);
                }
                if(!categoriaPeso.contains("-78")){
                    CB78.setEnabled(false);
                }
                if(!categoriaPeso.contains("+78")){
                    CB78M.setEnabled(false);
                }

                if (!categoriaPeso.contains("-38")){
                    CB38.setEnabled(false);
                }
                if(!categoriaPeso.contains("-42")){
                    CB42.setEnabled(false);
                }
                if(!categoriaPeso.contains("-46")){
                    CB46.setEnabled(false);
                }
                if(!categoriaPeso.contains("-50")){
                    CB50.setEnabled(false);
                }
                if(!categoriaPeso.contains("-55")){
                    CB55.setEnabled(false);
                }
                if(!categoriaPeso.contains("-60")){
                    CB60.setEnabled(false);
                }
                if(!categoriaPeso.contains("-66")){
                    CB66.setEnabled(false);
                }
                if(!categoriaPeso.contains("-73")){
                    CB73.setEnabled(false);
                }
                if(!categoriaPeso.contains("-81")){
                    CB81.setEnabled(false);
                }
                if(!categoriaPeso.contains("+81")){
                    CB81M.setEnabled(false);
                }
                if(!categoriaPeso.contains("-90")){
                    CB90.setEnabled(false);
                }
                if(!categoriaPeso.contains("+90")){
                    CB90M.setEnabled(false);
                }
                if(!categoriaPeso.contains("-100")){
                    CB100.setEnabled(false);
                }
                if(!categoriaPeso.contains("+100")){
                    CB100M.setEnabled(false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ButtonGroup grupoCategoriasPesoMasculino(){
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(CB38);
        grupo.add(CB42);
        grupo.add(CB46);
        grupo.add(CB50);
        grupo.add(CB55);
        grupo.add(CB60);
        grupo.add(CB66);
        grupo.add(CB73);
        grupo.add(CB81);
        grupo.add(CB81M);
        grupo.add(CB90);
        grupo.add(CB90M);
        grupo.add(CB100);
        grupo.add(CB100M);
        return grupo;
    }

    private ButtonGroup grupoCategoriasPesoFeminino(){
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(CB40);
        grupo.add(CB44);
        grupo.add(CB48);
        grupo.add(CB52);
        grupo.add(CB57);
        grupo.add(CB63);
        grupo.add(CB70);
        grupo.add(CB70M);
        grupo.add(CB78);
        grupo.add(CB78M);
        return grupo;
    }

    private void btnCriarActionPerformed(ActionEvent actionEvent) {
        // TODO: CRIAR PROVA

        if (validarNumeroCampos() != 0){
            mostrarErro(validarNumeroCampos());
            return;
        }

        if (validarGenero() != 0){
            mostrarErro(validarGenero());
            return;
        }

        if (validarCategoriaEtaria() != 0){
            mostrarErro(validarCategoriaEtaria());
            return;
        }

        if (validarCategoriaPeso() != 0){
            mostrarErro(validarCategoriaPeso());
            return;
        }

        adicionarProvaJSON();
        abrirPaginaProvas();
    }

    private void mostrarErro (int codigo){

        switch(codigo){
            case 1:
                JOptionPane.showMessageDialog(mainPanel, "Necessário selecionar uma opção para cada campo");
                break;
            case 2:
                JOptionPane.showMessageDialog(mainPanel, "Género é mandatório");
                break;
            case 3:
                JOptionPane.showMessageDialog(mainPanel, "Escalão etário é mandatório");
                break;
            case 4:
                JOptionPane.showMessageDialog(mainPanel, "Categoria de Peso é mandatório");
                break;
            case 5:
                JOptionPane.showMessageDialog(mainPanel, "Categoria de Peso não coincide com o Género");
                break;
        }
    }

    private int validarNumeroCampos(){

        String escalaoEtario = (String) dropdownEscalaoEtario.getSelectedItem();

        if ((femininoRadioButton.isSelected() || masculinoRadioButton.isSelected()) && escalaoEtario != null && (categoriasPesoFemininoSelected() || categoriasPesoMasculinoSelected())){
            return 0;
        }

        return 1;
    }

    private boolean categoriasPesoFemininoSelected() {
        return CB40.isSelected() || CB44.isSelected() || CB48.isSelected() || CB52.isSelected() || CB57.isSelected() || CB63.isSelected() || CB70.isSelected() || CB70M.isSelected() || CB78.isSelected() || CB78M.isSelected();
    }

    private boolean categoriasPesoMasculinoSelected() {
        return CB38.isSelected() || CB42.isSelected() || CB46.isSelected() || CB50.isSelected() || CB55.isSelected() || CB60.isSelected() || CB66.isSelected() || CB73.isSelected() || CB81.isSelected() || CB81M.isSelected() || CB90.isSelected() || CB90M.isSelected() || CB100.isSelected() || CB100M.isSelected();
    }

    private int validarGenero(){
        if (femininoRadioButton.isSelected() || masculinoRadioButton.isSelected()){
            return 0;
        }

        return 2;
    }

    private int validarCategoriaEtaria(){
        String escalaoEtario = (String) dropdownEscalaoEtario.getSelectedItem();

        if  (escalaoEtario == null){
            return 3;
        }

        return 0;
    }

    private int validarCategoriaPeso(){

        if (!categoriasPesoMasculinoSelected() && !categoriasPesoFemininoSelected()){
            return 4;
        }


        if (femininoRadioButton.isSelected() && categoriasPesoMasculinoSelected()){
            return 5;
        }

        if (masculinoRadioButton.isSelected() && categoriasPesoFemininoSelected()){
            return 5;
        }

        return 0;
    }


    private void btnCancelarActionPerformed(ActionEvent actionEvent) {
        abrirPaginaProvas();
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
        Resultados.abrirPaginaResultados();
        this.dispose();
    }

    private void btnCalendarioActionPerformed(ActionEvent actionEvent) {
        CalendarioEventos.abrirPaginaCalendario();
        this.dispose();
    }

    private void btnResetCampos(ActionEvent actionEvent){
        System.out.println("eu");
        btnGroupM.clearSelection();
        btnGroupF.clearSelection();
    }

    private void fillComboBoxEscaloes(JComboBox<String> dropdownEscalaoEtario) {
        for (String escalao: escaloes){
            dropdownEscalaoEtario.addItem(escalao);
        }
    }

    private void adicionarProvaJSON(){

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonArray.get(idEvento);

            JSONObject prova = new JSONObject();

            if (masculinoRadioButton.isSelected()){
                prova.put("genero","Masculino");
            }else{
                prova.put("genero","Feminino");
            }

            prova.put("escalaoEtario",dropdownEscalaoEtario.getSelectedItem());

            String categoriasPeso = "";
            if (CB40.isSelected()){
                categoriasPeso += "-40kg";
            }
            if(CB44.isSelected()){
                categoriasPeso += "-44kg";
            }
            if(CB48.isSelected()){
                categoriasPeso += "-48kg";
            }
            if(CB52.isSelected()){
                categoriasPeso += "-52kg";
            }
            if(CB57.isSelected()){
                categoriasPeso += "-57kg";
            }
            if(CB63.isSelected()){
                categoriasPeso += "-63kg";
            }
            if(CB70.isSelected()){
                categoriasPeso += "-70kg";
            }
            if(CB70M.isSelected()){
                categoriasPeso += "+70kg";
            }
            if(CB78.isSelected()){
                categoriasPeso += "-78kg";
            }
            if(CB78M.isSelected()){
                categoriasPeso += "+78kg";
            }

            if (CB38.isSelected()){
                categoriasPeso += "-38kg";
            }
            if(CB42.isSelected()){
                categoriasPeso += "-42kg";
            }
            if(CB46.isSelected()){
                categoriasPeso += "-46kg";
            }
            if(CB50.isSelected()){
                categoriasPeso += "-50kg";
            }
            if(CB55.isSelected()){
                categoriasPeso += "-55kg";
            }
            if(CB60.isSelected()){
                categoriasPeso += "-60kg";
            }
            if(CB66.isSelected()){
                categoriasPeso += "-66kg";
            }
            if(CB73.isSelected()){
                categoriasPeso += "-73kg";
            }
            if(CB81.isSelected()){
                categoriasPeso += "-81kg";
            }
            if(CB81M.isSelected()){
                categoriasPeso += "+81kg";
            }
            if(CB90.isSelected()){
                categoriasPeso += "-90kg";
            }
            if(CB90M.isSelected()){
                categoriasPeso += "+90kg";
            }
            if(CB100.isSelected()){
                categoriasPeso += "-100kg";
            }
            if(CB100M.isSelected()){
                categoriasPeso += "+100kg";
            }
            prova.put("categoriaPeso",categoriasPeso);

            JSONArray provasArray = (JSONArray) jsonObject.get("provas");

            provasArray.add(prova);

            if (idEvento >= 0 && idEvento < jsonArray.size()) {
                jsonArray.set(idEvento, jsonObject);

                try (FileWriter fileWriter = new FileWriter("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonData = gson.toJson(jsonArray);

                    fileWriter.write(jsonData);
                    JOptionPane.showMessageDialog(mainPanel, "Prova criada e associada");
                } catch (IOException e) {
                    //System.out.println("Ocorreu um erro ao escrever o JSON atualizado no ficheiro: " + e.getMessage());
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao criar a prova");
                }
            } else {
                //System.out.println("ID inválido. O objeto com o ID especificado não existe.");
                JOptionPane.showMessageDialog(mainPanel, "Erro ao associar a prova");
            }

        } catch (IOException | org.json.simple.parser.ParseException e) {
            //System.out.println("Ocorreu um erro ao ler o arquivo JSON: " + e.getMessage());
            JOptionPane.showMessageDialog(mainPanel, "Erro ao associar a prova");
        }

    }
}
