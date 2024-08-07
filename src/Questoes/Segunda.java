package Questoes;

import Programas.VerificadorDePlagioHash;
import java.util.LinkedList;
import Estruturas.HashTableDinamica;
import Estruturas.ArvoreAVL;
import Programas.VerificadorDePlagioAVL;

public class Segunda {

    static long comparacoes = 0L;
    static long movimentacoes = 0L;

    public void LetraA(String[] documentos, String documentoPlagio, Integer m) {
        comparacoes = 0;
        movimentacoes = 0;

        HashTableDinamica<String, LinkedList<String>> tabelaHash = new HashTableDinamica<>();
        VerificadorDePlagioHash verificador = new VerificadorDePlagioHash(tabelaHash);

        long tempoinicial = System.currentTimeMillis();
        for(String documento : documentos){
            verificador.inserir(documento);
        }
        verificador.inserir(documentoPlagio);

        boolean teste = verificador.verificarPlagio(documentoPlagio, m);
        if (teste == true){
            System.out.println("Plágio detectado\n");
        }
        else{
            System.out.println("Plágio não detectado\n");
        }
        long tempofinal = System.currentTimeMillis();
        long tempoDeExecucao = tempofinal - tempoinicial;

        comparacoes += verificador.getComparacoes();
        movimentacoes += verificador.getMovimentacoes();
        System.out.println("|Tempo de Execução: " + tempoDeExecucao + "ms" +
                           "\n|Movimentações: "     + movimentacoes +
                           "\n|Comparações: "       + comparacoes + "\n");
    }

    public void LetraB(String[] documentos, String documentoPlagio, Integer m) {
        comparacoes = 0;
        movimentacoes = 0;

        ArvoreAVL arvore = new ArvoreAVL();
        VerificadorDePlagioAVL verificador = new VerificadorDePlagioAVL(arvore);

        long tempoinicial = System.currentTimeMillis();
        for(String documento : documentos){
            verificador.inserir(documento);
        }
        verificador.inserir(documentoPlagio);

        boolean teste = verificador.verificarPlagio(documentoPlagio, m);
        if (teste == true){
            System.out.println("Plágio detectado\n");
        }
        else{
            System.out.println("Plágio não detectado\n");
        }
        long tempofinal = System.currentTimeMillis();
        long tempoDeExecucao = tempofinal - tempoinicial;

        comparacoes += verificador.getComparacoes();
        movimentacoes += verificador.getMovimentacoes();
        System.out.println("|Tempo de Execução: " + tempoDeExecucao + "ms" +
                           "\n|Movimentações: "     + movimentacoes +
                           "\n|Comparações: "       + comparacoes + "\n");
    }

}

