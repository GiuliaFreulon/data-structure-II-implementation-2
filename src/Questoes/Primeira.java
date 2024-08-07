package Questoes;

import Auxiliares.Gerador;
import Entidades.Empregado;
import Estruturas.MultiMapa;

public class Primeira {
    static long comparacoes = 0L;
    static long movimentacoes = 0L;

    private MultiMapa<Integer, Empregado> multimapa;
    public Primeira(int tamanho) { multimapa = new MultiMapa<>(tamanho); }

    public void put(int K) {
        comparacoes = 0;
        movimentacoes = 0;

        Empregado empregado = Gerador.Geradores.geradorEmpregado(K);
        boolean teste = multimapa.put(K, empregado);

        if(teste == true){
            System.out.println("Chave inserida com sucesso\n");
        }
        else{
            System.out.println("Não foi possível inserir a chave\n");
        }

        comparacoes += multimapa.getComparacoes();
        movimentacoes += multimapa.getMovimentacoes();
        System.out.println("|Movimentações: "     + movimentacoes +
                           " |Comparações: "       + comparacoes + "\n");
    }

    public void findAll(int K) {
        comparacoes = 0;
        movimentacoes = 0;

        if(multimapa.findAll(K) != null) {
            System.out.println(multimapa.findAll(K) + "\n");
        }
        else{
            System.out.println("Chave não encontrada\n");
        }

        comparacoes += multimapa.getComparacoes();
        movimentacoes += multimapa.getMovimentacoes();
        System.out.println("|Movimentações: "     + movimentacoes +
                           " |Comparações: "       + comparacoes + "\n");
    }

    public long getComparacoes() {
        return comparacoes;
    }
    public long getMovimentacoes() {
        return movimentacoes;
    }
}
