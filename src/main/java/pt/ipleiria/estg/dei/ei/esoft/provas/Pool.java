package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.atletas.Atleta;

import java.util.List;

public class Pool {
    private char identificacao;
    private List<Combate> combates;
    private Atleta vencedor;
    private List<Atleta> atletas;

    public Pool(char identificacao){
        // verificar valor
        this.identificacao = identificacao;
    }

    private void adicionarAtletasPool (Atleta atleta){
        atletas.add(atleta);
    }

}
