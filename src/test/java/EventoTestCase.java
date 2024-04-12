
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class EventoTestCase {

    @Test
    public void criarEventoValido() throws ParseException {

        // quando o utulizador cria um preencho os campos do evento é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        String pesosString = "-40kg -44kg -48kg -52kg -50kg -55kg -57kg -60kg -63kg -66kg -70kg +70kg -73kg -81kg -90kg +90kg";
        String[] pesos = pesosString.split(" ");
        List<String> listaCategoriasPeso = Arrays.asList(pesos);

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String datas = "19/01/2024;20/01/2024";
        String[] datasSeparadas = datas.split(";");
        Date dataInicio = formatador.parse(datasSeparadas[0]);
        Date dataFinal = formatador.parse(datasSeparadas[1]);

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        String generos = "Masculino Feminino";
        String[] dadosSeparados = generos.split(" ");
        List<Genero> listaGenero = new ArrayList<>();
        for (String valor : dadosSeparados) {
            Genero genero = Genero.valueOf(valor);
            listaGenero.add(genero);
        }

        var evento = new Evento("Taça Distrital de Judo",dataInicio,dataFinal,"Portugal","Leiria, Pavilhão Desportivo dos Pousos",
                escalaoEtario,listaCategoriasPeso,listaGenero,"Judo");

        assertEquals ("Taça Distrital de Judo",evento.getNome());
        assertEquals(dataInicio,evento.getDataInicio());
        assertEquals(dataFinal,evento.getDataFim());
        assertEquals("Portugal",evento.getPais());
        assertEquals("Leiria, Pavilhão Desportivo dos Pousos",evento.getLocal());
        assertEquals(escalaoEtario.toString(),evento.getEscalaoEtario());
        assertEquals(pesosString,evento.getCategoriasPeso());
        assertEquals(generos,evento.getGenero());
        assertEquals("Judo",evento.getModalidade());

    }

    @Test
    public void criarEventoNomeInvalidoNumeros() throws ParseException {

        // quando o utulizador cria um preencho os campos do evento é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        String pesosString = "-40kg -44kg -48kg -52kg -50kg -55kg -57kg -60kg -63kg -66kg -70kg +70kg -73kg -81kg -90kg +90kg";
        String[] pesos = pesosString.split(" ");
        List<String> listaCategoriasPeso = Arrays.asList(pesos);

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String datas = "19/01/2024;20/01/2024";
        String[] datasSeparadas = datas.split(";");
        Date dataInicio = formatador.parse(datasSeparadas[0]);
        Date dataFinal = formatador.parse(datasSeparadas[1]);

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        String generos = "Masculino Feminino";
        String[] dadosSeparados = generos.split(" ");
        List<Genero> listaGenero = new ArrayList<>();
        for (String valor : dadosSeparados) {
            Genero genero = Genero.valueOf(valor);
            listaGenero.add(genero);
        }

        String nome = "Campeonato Europeu 2024";

        System.out.println("Nome não pode ter caracteres numéricos\nNome: " + nome);

        assertThrows(IllegalArgumentException.class, () -> {
            new Evento(nome,dataInicio,dataFinal,"Portugal","Leiria, Pavilhão Desportivo dos Pousos",
                    escalaoEtario,listaCategoriasPeso,listaGenero,"Judo");
        });
    }

    @Test
    public void criarEventoNomeInvalidoTamanhoMaximo() throws ParseException {

        // quando o utulizador cria um preencho os campos do evento é isto que acontece - vai buscar os valores (nao é estatico como aqui)

        String pesosString = "-40kg -44kg -48kg -52kg -50kg -55kg -57kg -60kg -63kg -66kg -70kg +70kg -73kg -81kg -90kg +90kg";
        String[] pesos = pesosString.split(" ");
        List<String> listaCategoriasPeso = Arrays.asList(pesos);

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String datas = "19/01/2024;20/01/2024";
        String[] datasSeparadas = datas.split(";");
        Date dataInicio = formatador.parse(datasSeparadas[0]);
        Date dataFinal = formatador.parse(datasSeparadas[1]);

        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf("Infantis");

        String generos = "Masculino Feminino";
        String[] dadosSeparados = generos.split(" ");
        List<Genero> listaGenero = new ArrayList<>();
        for (String valor : dadosSeparados) {
            Genero genero = Genero.valueOf(valor);
            listaGenero.add(genero);
        }

        // nome inválido - nao tamanho maximo 50 caracteres - nome dado com 51 caracteres

        String nome = "testetamanhomaximoootestetamanhomaximoootestetamanh";
        System.out.println("Tamanho máximo: 50 caracteres\nTamanho nome: " + nome.length());

        assertThrows(IllegalArgumentException.class, () -> {
            new Evento(nome,dataInicio,dataFinal,"Portugal","Leiria, Pavilhão Desportivo dos Pousos",
                    escalaoEtario,listaCategoriasPeso,listaGenero,"Judo");
        });

    }


}
