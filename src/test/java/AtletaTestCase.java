import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.atletas.Atleta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtletaTestCase {
    @Test
    public void criarAtletaValido() throws ParseException {

        // quando o utulizador cria um preencho os campos do atleta é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataIn = "09/10/2003";
        Date dataNascimento = formatador.parse(dataIn);

        Genero genero = Genero.Masculino;

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        var atleta = new Atleta("David Rino","Portugal",genero, "Judo",65,dataNascimento,312311234,escalaoEtario);

        assertEquals ("David Rino",atleta.getNome());
        assertEquals(dataNascimento,atleta.getDataNascimento());
        assertEquals("Portugal",atleta.getNacionalidade());
        assertEquals(genero,atleta.getGenero());
        assertEquals(65,atleta.getPeso());
        assertEquals(312311234,atleta.getContacto());
        assertEquals(escalaoEtario,atleta.getEscalaoEtario());
        assertEquals("Judo",atleta.getModalidade());
    }

    @Test
    public void criarAtletaNomeInvalidoNumeros() throws ParseException {

        // quando o utulizador cria um preencho os campos do atleta é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataIn = "09/10/2003";
        Date dataNascimento = formatador.parse(dataIn);

        Genero genero = Genero.Masculino;

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        String nome = "David Rino 2003";

        System.out.println("Nome não pode ter caracteres numéricos\nNome: " + nome);

        assertThrows(IllegalArgumentException.class, () -> {
            new Atleta(nome,"Portugal",genero, "Judo",65,dataNascimento,312311234,escalaoEtario);
        });
    }

    @Test
    public void criarAtletaNomeInvalidoTamanhoMaximo() throws ParseException {

        // quando o utulizador cria um preencho os campos do evento é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataIn = "09/10/2003";
        Date dataNascimento = formatador.parse(dataIn);

        Genero genero = Genero.Masculino;

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        String nome = "testecaracteresmaximosatletatestecaracteresmaximosatletatestecaracteresmaximosatletatestecaracteresmaximosatleta";
        System.out.println("Tamanho máximo: 50 caracteres\nTamanho nome: " + nome.length());

        assertThrows(IllegalArgumentException.class, () -> {
            new Atleta(nome, "Portugal", genero, "Judo", 65, dataNascimento, 312311234, escalaoEtario);
        });
    }

    @Test
    public void criarAtletaContactoInvalidoTamanho() throws ParseException {

        // quando o utulizador cria um preencho os campos do evento é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataIn = "09/10/2003";
        Date dataNascimento = formatador.parse(dataIn);

        Genero genero = Genero.Masculino;

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        String nome = "David Rino";
        int contacto = 2313222;
        String contactoStr = Integer.toString(contacto);
        System.out.println("Contacto tamanho de: 9 caracteres\nTamanho introduzido: " + contactoStr.length());

        assertThrows(IllegalArgumentException.class, () -> {
            new Atleta(nome, "Portugal", genero, "Judo", 65, dataNascimento, contacto, escalaoEtario);
        });
    }

    @Test
    public void criarAtletaPesoInvalidoCaracteres() throws ParseException {

        // quando o utulizador cria um preencho os campos do evento é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataIn = "09/10/2003";
        Date dataNascimento = formatador.parse(dataIn);

        Genero genero = Genero.Masculino;

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        String nome = "David Rino";
        String pesoSTR = "10.30492";

        float peso = Float.parseFloat(pesoSTR);

        System.out.println("Peso inválido");

        assertThrows(IllegalArgumentException.class, () -> {
            new Atleta(nome, "Portugal", genero, "Judo", peso, dataNascimento, 312311234, escalaoEtario);
        });
    }

}
