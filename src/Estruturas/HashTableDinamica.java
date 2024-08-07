package Estruturas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HashTableDinamica<K, V> {

    static long comparacoes = 0L;
    static long movimentacoes = 0L;

    private ArrayList<ArrayList<Par<K, V>>> tabela;
    private int tamanho;

    public HashTableDinamica() {
        tabela = new ArrayList<>();
        tamanho = 0;
        for (int i = 0; i < 16; i++) {
            tabela.add(new ArrayList<>());
        }
    }

    // Método para inserir um par chave-valor na tabela
    public void inserir(K chave, V valor) {
        movimentacoes = 0;
        comparacoes = 0;

        int hash = chave.hashCode(); // Função de hash que calcula o índice com base na chave
        int indice = Math.abs(hash % tabela.size()); // Calcula o índice com base na chave
        ArrayList<Par<K, V>> lista = tabela.get(indice); // Obtém a lista associada a esse índice na tabela
        movimentacoes += 3;

        for (Par<K, V> par : lista) { // Itera sobre os pares(chave, valor) na lista
            if (par.getChave().equals(chave)) { // Compara as chaves da lista com a chave que estamos tentando inserir
                comparacoes++;
                par.setValor(valor); // Caso alguma chave da lista seja igual a inserida, seu valor é atualizado
                return; // Sai da função, pois a inserção foi concluída
            }
        }

        lista.add(new Par<>(chave, valor)); // Se a chave não existe na lista, adiciona um novo par
        movimentacoes++;
        tamanho++;

        // Verifica se a tabela atingiu 75% de sua capacidade
        if (tamanho > tabela.size() * 0.75) {
            comparacoes++;
            redimensionar(); // Se sim, redimensiona a tabela
        }
    }

    // Método para retornar o valor associado a uma chave
    public V retornar(K chave) {
        movimentacoes = 0;
        comparacoes = 0;

        int hash = chave.hashCode(); // Função de hash que calcula o índice com base na chave
        int indice = Math.abs(hash % tabela.size()); // Calcula o índice com base na chave
        ArrayList<Par<K, V>> lista = tabela.get(indice); // Obtém a lista associada a esse índice na tabela
        movimentacoes += 3;

        for (Par<K, V> par : lista) { // Itera sobre os pares(chave, valor) na lista
            if (par.getChave().equals(chave)) { // Compara as chaves da lista com a chave que estamos tentando inserir
                comparacoes++;
                return par.getValor(); // Caso alguma chave da lista seja igual a procurada, seu valor é retornado
            }
        }

        return null; // Se não encontrou nenhuma chave igual a procurada, retorna null
    }

    // Método para retornar um conjunto contendo todas as chaves da HashTable
    public Set<K> SetDeChaves() {
        Set<K> set = new HashSet<>();
        
        for (ArrayList<Par<K, V>> lista : tabela) { // Percorre as listas da HashTable
            for (Par<K, V> par : lista) { // Percorre os pares das listas
                set.add(par.getChave()); // Adiciona os pares ao conjunto
                movimentacoes++;
            }
        }
        return set; // Retorna o conjunto com as chaves
    }

    // Método para redimensionar a HashTable
    private void redimensionar() {
        ArrayList<ArrayList<Par<K, V>>> tabelaAntiga = tabela; // Faz uma cópia da tabela original
        tabela = new ArrayList<>(); // Cria uma nova tabela
        tamanho = 0; // Reseta o tamanho da tabela para 0

        for (int i = 0; i < tabelaAntiga.size() * 2; i++) { // Percorre a tabela antiga e multiplica seu tamanho por 2
            tabela.add(new ArrayList<>()); // Adiciona novas listas vazias à nova tabela
            movimentacoes++;
        }
        for (ArrayList<Par<K, V>> lista : tabelaAntiga) { // Percorre as listas da tabela antiga
            for (Par<K, V> par : lista) { // Percorre os pares das listas
                inserir(par.getChave(), par.getValor()); // Reinsere cada par na nova tabela
            }
        }
    }

    public static class Par<K, V> {
        private K chave;
        private V valor;

        public Par(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public K getChave() {
            return chave;
        }
        public V getValor() {
            return valor;
        }

        public void setValor(V valor) {
            this.valor = valor;
        }
    }

    public long getComparacoes() {
        return comparacoes;
    }
    public long getMovimentacoes() {
        return movimentacoes;
    }
}