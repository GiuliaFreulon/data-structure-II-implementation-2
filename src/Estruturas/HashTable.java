package Estruturas;

import java.util.LinkedList;

public class HashTable<K, V> {

    static long comparacoes = 0L;
    static long movimentacoes = 0L;

    private LinkedList<Entrada<K, V>>[] tabela;
    public HashTable(int capacidade) { tabela = new LinkedList[capacidade]; }

    // Método para inserir um par chave-valor na tabela
    public void inserir(K chave, V valor) {
        movimentacoes = 0;
        comparacoes = 0;

        int indice = hash(chave); // Calcula o índice com base na chave
        movimentacoes++;

        // Caso ainda não exista uma lista para esse índice
        if (tabela[indice] == null) {
            tabela[indice] = new LinkedList<>(); // Cria uma lista para esse índice

            comparacoes++;
            movimentacoes++;
        }

        tabela[indice].add(new Entrada<>(chave, valor)); // Adiciona a entrada no índice
        movimentacoes++;
    }

    // Método para retornar o valor associado a uma chave
    public V retornar(K chave) {
        movimentacoes = 0;
        comparacoes = 0;

        int indice = hash(chave); // Calcula o índice com base na chave
        movimentacoes++;

        // Caso não encontre uma lista para esse índice
        if (tabela[indice] == null) {
            comparacoes++;
            return null;
        }

        // Caso encontre uma lista para esse índice
        for (Entrada<K, V> Entrada : tabela[indice]) { // Percorre a lista ligada do índice
            if (Entrada.getchave().equals(chave)) {
                comparacoes++;
                return Entrada.getvalor(); // Retorna o valor associado à chave
            }
        }

        return null; // Retorna null se a chave não for encontrada
    }

    // Método para verificar se a tabela contém uma chave específica
    public boolean contemChave(K chave) {
        int indice = hash(chave); // Calcula o índice com base na chave
        movimentacoes++;

        // Caso não encontre uma lista para esse índice
        if (tabela[indice] == null) {
            comparacoes++;
            return false; // Retorna falso se a chave não existir
        }

        // Caso encontre uma lista para esse índice
        for (Entrada<K, V> Entrada : tabela[indice]) { // Percorre a lista ligada do índice
            if (Entrada.getchave().equals(chave)) {
                comparacoes++;
                return true; // Retorna true se a chave for encontrada
            }
        }

        return false; // Retorna falso se a chave não for encontrada
    }

    // Função de hash que calcula o índice com base na chave
    private int hash(K chave) {
        return Math.abs(chave.hashCode() % tabela.length);
    }

    // Classe que representa uma entrada na tabela com chave e valor
    private static class Entrada<K, V> {
        private K chave;
        private V valor;

        public Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public K getchave() {
            return chave;
        }
        public V getvalor() {
            return valor;
        }
    }

    public long getComparacoes() {
        return comparacoes;
    }
    public long getMovimentacoes() {
        return movimentacoes;
    }
}