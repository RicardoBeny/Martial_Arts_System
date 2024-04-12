package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;
import pt.ipleiria.estg.dei.ei.esoft.provas.Prova;

public class Inscricao {
    private Atleta atleta;
    private Prova prova;
    private float pesoInscricao;
    private EscalaoEtario escalaoEtario;

    public Inscricao (Atleta atleta, Prova prova, float pesoInscricao, EscalaoEtario escalaoEtario){
        // ja se verifica que o atleta existe e o evento
        this.atleta = atleta;
        this.prova = prova;
        // verificar peso
        this.pesoInscricao = pesoInscricao;
        this.escalaoEtario = escalaoEtario;
    }

    public float getPesoInscricao() {
        return pesoInscricao;
    }

    public Prova getProva() {
        return prova;
    }

    public void atletarPesoInscricao (float pesoNovo){
        // verificar se peso novo valido
        this.pesoInscricao = pesoNovo;
    }

}
