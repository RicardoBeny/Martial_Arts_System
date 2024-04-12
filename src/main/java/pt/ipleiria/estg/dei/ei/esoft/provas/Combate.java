package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.atletas.Atleta;

import java.util.Date;
import java.util.List;


public class Combate {
    private List<Atleta> atletas;
    private Atleta vencedor;
    private Atleta perdedor;
    private Date horaInicio;

    public Combate (Date horaInicio){
        this.horaInicio = horaInicio;
    }

    private void adicionarAtleta (Atleta atleta){
        atletas.add(atleta);
    }

}
