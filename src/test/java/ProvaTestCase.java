import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;
import pt.ipleiria.estg.dei.ei.esoft.provas.Prova;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProvaTestCase {

    @Test
    public void criarProvaValida() throws ParseException {
        Random random = new Random();
        int hora = random.nextInt(24);
        int minutos = random.nextInt(60);
        Date horaInicio = new Date();
        horaInicio.setHours(hora);
        horaInicio.setMinutes(minutos);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaInicio);
        calendar.add(Calendar.MINUTE, minutos);
        Date horaFinal = calendar.getTime();

        String categoriaPeso = "-40kg";

        String escalaoEtarioString = "Cadetes";
        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf(escalaoEtarioString);

        String generoString = "Feminino";
        Genero genero = Genero.valueOf(generoString);

        Evento evento =  eventoProva();

        Prova prova = new Prova(horaInicio,horaFinal,categoriaPeso,escalaoEtario,genero,evento);

        assertEquals(horaInicio,prova.getHoraInicio());
        assertEquals(horaFinal,prova.getHoraFim());
        assertEquals(categoriaPeso,prova.getCategoriaPeso());
        assertEquals(escalaoEtarioString,prova.getEscalaoEtario());
        assertEquals(generoString,prova.getGenero());
        assertEquals(evento,prova.getEvento());
    }

    @Test
    public void criarProvaInvalida() throws ParseException {
        Random random = new Random();
        int hora = random.nextInt(24);
        int minutos = random.nextInt(60);
        Date horaInicio = new Date();
        horaInicio.setHours(hora);
        horaInicio.setMinutes(minutos);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaInicio);
        calendar.add(Calendar.MINUTE, minutos);
        Date horaFinal = calendar.getTime();

        String categoriaPeso = "";

        String escalaoEtarioString = "Cadetes";
        EscalaoEtario escalaoEtario = EscalaoEtario.valueOf(escalaoEtarioString);

        String generoString = "Feminino";
        Genero genero = Genero.valueOf(generoString);

        Evento evento =  eventoProva();

        System.out.println("Categoria Peso tem de ser indicada\nCategoria Peso: " + categoriaPeso);

        assertThrows(IllegalArgumentException.class, () -> {
            new Prova(horaInicio,horaFinal,categoriaPeso,escalaoEtario,genero,evento);
        });
    }

    private Evento eventoProva () throws ParseException {
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

        return evento;
    }
}
