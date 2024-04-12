package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Atleta {
    private String nome;
    private String nacionalidade;
    private Genero genero;
    private String modalidade;
    private float peso;
    private Date dataNascimento;
    private int contacto;
    private EscalaoEtario escalaoEtario;
    private int idade;
    private List<Inscricao> inscricoes;

    public Atleta(String nome, String nacionalidade, Genero genero, String modalidade, float peso, Date dataNascimento, int contacto, EscalaoEtario escalaoEtario) {

        if (!validarNome(nome)){
            throw new IllegalArgumentException("Nome inválido - não pode ser vazio, ter caracteres numéricos/especiais e tem de ter tamanho máximo de 50 caracteres");
        }

        if (!validarContacto(contacto)){
            throw new IllegalArgumentException("Contacto inválido - não pode ser vazio, ter caracteres especiais ou letras e tem de ter tamanho de 9 caracteres");
        }

        if (!validarPeso(peso)){
            throw new IllegalArgumentException("Peso inválido - não pode ser vazio, ter caracteres especiais ou letras e tem de ter no maximo 2 casas decimais");
        }

        this.nacionalidade = nacionalidade;
        this.genero = genero;
        this.modalidade = modalidade;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.contacto = contacto;
        this.escalaoEtario = escalaoEtario;
    }

    private boolean validarPeso(float peso) {

        String pesoStr = Float.toString(peso);

        if (pesoStr.isEmpty()){
            return false;
        }

        if (!Pattern.matches(".*\\d.*", pesoStr)) {
            return false;
        }

        if (pesoStr == null) {
            return false; // Null peso value
        }

        String pesoString = String.valueOf(peso);
        int indexOfDecimalSeparator = pesoString.indexOf(".");

        if (indexOfDecimalSeparator != -1) {
            int decimalPlaces = pesoString.length() - indexOfDecimalSeparator - 1;
            if(decimalPlaces > 2){
                return false;
            }
        }

        return true;
    }

    private boolean validarNome(String nome) {

        if (nome.isEmpty()){
            return false;
        }

        if (Pattern.matches(".*\\d.*", nome)) {
            return false;
        }

        if (!Pattern.matches("^[a-zA-Z ç]+$", nome)){
            return false;
        }

        if (nome.length() > 50){
            return false;
        }

        return true;
    }

    private boolean validarContacto(int contacto) {
        String contactoStr = Integer.toString(contacto);

        if (contactoStr.isEmpty()){
            return false;
        }

        if (contactoStr.length() != 9){
            return false;
        }

        if (!Pattern.matches(".*\\d.*", contactoStr)) {
            return false;
        }
        return true;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getModalidade() {
        return modalidade;
    }

    public float getPeso() {
        return peso;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public int getContacto() {
        return contacto;
    }

    public EscalaoEtario getEscalaoEtario() {
        return escalaoEtario;
    }
}
