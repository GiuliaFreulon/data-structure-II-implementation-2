package Estruturas;

import java.util.ArrayList;
public class MultiMapa<K, V> {

    static long comparacoes = 0L;
    static long movimentacoes = 0L;

    private HashTable<K, ArrayList<V>> tabela;
    public MultiMapa(int tamanho) { tabela = new HashTable<>(tamanho); } // Cria uma HashTable para o MultiMapa

    // Método para verificar se a existência ou não de um valor específico associado a uma chave
    private boolean chaveValorIgual(K chave, V valor) {
        // Percorre a lista de valores associada à chave
        for (V v : tabela.retornar(chave)) {
            if (v.toString().equals(valor.toString())) {
                comparacoes++;
                comparacoes += tabela.getComparacoes();
                movimentacoes += tabela.getMovimentacoes();
                return true; // Retorna true se encontrar um valor idêntico já associado à chave
            }
        }
        return false; // Retorna false se não encontrar esse valor associado à chave
    }

    // Método para adicionar um par chave-valor ao multimapa
    public boolean put(K chave, V valor) {
        movimentacoes = 0;
        comparacoes = 0;

        // Caso a chave não exista na tabela
        if (!tabela.contemChave(chave)) {
            comparacoes++;

            tabela.inserir(chave, new ArrayList<>()); // Cria uma lista vazia para a chave
            movimentacoes += tabela.getMovimentacoes();
            comparacoes += tabela.getComparacoes();

            tabela.retornar(chave).add(valor); // Adiciona o valor à lista dessa chave
            movimentacoes += tabela.getMovimentacoes();
            comparacoes += tabela.getComparacoes();

            return true; // Retorna true para indicar que o valor foi adicionado
        }

        // Caso a chave já exista na tabela
        else {
            comparacoes++;

            // Caso o par chave-valor já exista no multimapa
            if (chaveValorIgual(chave, valor)) {
                System.out.println("Mesma chave com mesmos valores já existente\n");

                comparacoes++;
                return false; // Retorna false para indicar que o valor não foi adicionado
            }

            // Caso o par chave-valor ainda não exista no multimapa
            else {
                tabela.retornar(chave).add(valor); // Adiciona o valor à lista dessa chave
                movimentacoes += tabela.getMovimentacoes();
                comparacoes += tabela.getComparacoes();

                comparacoes++;
                return true; // Retorna true para indicar que o valor foi adicionado
            }
        }
    }

    // Método para recuperar todos os valores associados a uma chave
    public ArrayList<V> findAll(K chave) {
        movimentacoes = 0;
        comparacoes = 0;

        ArrayList<V> retorno = tabela.retornar(chave);
        movimentacoes += tabela.getMovimentacoes();
        comparacoes += tabela.getComparacoes();

        return retorno;
    }

    public long getComparacoes() {
        return comparacoes;
    }
    public long getMovimentacoes() {
        return movimentacoes;
    }
}
