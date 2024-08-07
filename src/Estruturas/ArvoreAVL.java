package Estruturas;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

class Documento {
    String nome;
    LinkedList<String> palavras;

    public Documento(String nome, LinkedList<String> palavras) {
        this.nome = nome;
        this.palavras = palavras;
    }
}

class No {
    Documento documento;
    No esquerda, direita;
    int altura;

    public No(Documento documento) {
        this.documento = documento;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;
    }
}

public class ArvoreAVL {
    static long comparacoes = 0L;
    static long movimentacoes = 0L;

    No raiz;

    // Método para verificar a altura de um nó
    private int altura(No no) {
        if (no == null) { // Verifica se o nó é nulo
            comparacoes++;
            return 0;
        }

        // Se não for, retorna a altura
        return no.altura;
    }

    // Método para calcular o fator de balanceamento de um nó
    private int fatorBalanceamento(No no) {
        if (no == null) { // Verifica se o nó é nulo
            comparacoes++;
            return 0;
        }

        // Se não for, retorna o fator de balanceamento
        return altura(no.esquerda) - altura(no.direita);
    }

    // Método para fazer uma rotação à direita no nó
    private No rotacaoDireita(No no) {
        No filhoEsquerdo = no.esquerda;
        No neto = filhoEsquerdo.direita;

        // Faz a rotação
        filhoEsquerdo.direita = no;
        no.esquerda = neto;

        // Atualiza a altura do nó e do filho esquerdo depois da rotação
        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
        filhoEsquerdo.altura = Math.max(altura(filhoEsquerdo.esquerda), altura(filhoEsquerdo.direita)) + 1;

        movimentacoes += 6;
        return filhoEsquerdo;
    }

    //Método para fazer uma rotação à esquerda no nó
    private No rotacaoEsquerda(No no) {
        No filhoDireito = no.direita;
        No T2 = filhoDireito.esquerda;

        // Faz a rotação
        filhoDireito.esquerda = no;
        no.direita = T2;

        // Atualiza a altura do nó e do filho direito depois da rotação
        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
        filhoDireito.altura = Math.max(altura(filhoDireito.esquerda), altura(filhoDireito.direita)) + 1;

        movimentacoes += 6;
        return filhoDireito;
    }

    // Método para inserir um nó com um documento na árvore
    private No inserir(No no, Documento documento) {
        if (no == null) { // Verifica se o nó que queremos inserir é nulo
            comparacoes++;
            movimentacoes++;
            return (new No(documento)); // Se for, cria um novo nó com o documento passado
        }

        // Se não for:
        if (documento.nome.compareTo(no.documento.nome) < 0) { //Verifica se o nome do documento passado é menor que o nome do documento no nó que tentamos inserir
            comparacoes++;
            no.esquerda = inserir(no.esquerda, documento); // Se for menor, insere na esquerda
        }
        else if (documento.nome.compareTo(no.documento.nome) > 0) { //Verifica se o nome do documento passado é maior que o nome do documento no nó que tentamos inserir
            comparacoes++;
            no.direita = inserir(no.direita, documento); // Se for maior, insere na esquerda
        }
        else { // Caso tenha os documentos tenham o mesmo número de caracteres
            no.direita = inserir(no.direita, documento); // Adota-se a direita por convenção

            if (documento.nome.equals(no.documento.nome)) { // Caso os nomes sejam idênticos, não insere
                comparacoes++;
                return no;
            }
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita)); // Atualiza a altura do nó após a inserção
        int fatorBalanceamento = fatorBalanceamento(no); // Calcula o fator de balanceamento do nó

        // Caso de desequilíbrio na sub-árvore esquerda e a nova chave é inserida na sub-árvore esquerda da desequilibrada
        if (fatorBalanceamento > 1 && documento.nome.compareTo(no.esquerda.documento.nome) < 0) {
            comparacoes++;
            // Rotação simples à direita
            return rotacaoDireita(no);
        }

        // Caso de desequilíbrio na sub-árvore direita e a nova chave é inserida na sub-árvore direita da desequilibrada
        if (fatorBalanceamento < -1 && documento.nome.compareTo(no.direita.documento.nome) > 0) {
            comparacoes++;
            // Rotação simples à esquerda
            return rotacaoEsquerda(no);
        }

        // Caso de desequilíbrio na sub-árvore esquerda e a nova chave é inserida na sub-árvore direita da desequilibrada
        if (fatorBalanceamento > 1 && documento.nome.compareTo(no.esquerda.documento.nome) > 0) {
            comparacoes++;
            // Rotação dupla à direita
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Caso de desequilíbrio na sub-árvore direita e a nova chave é inserida na sub-árvore esquerda da desequilibrada
        if (fatorBalanceamento < -1 && documento.nome.compareTo(no.direita.documento.nome) < 0) {
            comparacoes++;
            // Rotação dupla à esquerda
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no; // Se não precisar balancear retorna o nó
    }

    // Método para inserir um documento como um nó
    public void inserirDocumento(String nome, LinkedList<String> palavras) {
        comparacoes = 0;
        movimentacoes = 0;

        Documento documento = new Documento(nome, palavras);
        raiz = inserir(raiz, documento);
    }

    // Método para buscar um documento com seu nome
    public LinkedList<String> buscarDocumento(String nomeDocumento) {
        comparacoes = 0;
        movimentacoes = 0;

        return buscar(raiz, nomeDocumento);
    }

    // Método para buscar um nó pelo nome de um documento
    private LinkedList<String> buscar(No no, String nomeDocumento) {
        if (no == null) { // Verifica se o nó que estamos buscando é nulo
            comparacoes++;
            return null;
        }

        if (no.documento.nome.equals(nomeDocumento)) { // Verifica se o nome do documento procurado é igual ao nome do documento do nó que estamos
            comparacoes++;
            return no.documento.palavras;
        }
        else if (no.documento.nome.compareTo(nomeDocumento) > 0) { // Verifica se o nome do documento procurado é menor que o nome do documento do nó que estamos
            comparacoes++;
            return buscar(no.esquerda, nomeDocumento); // Se for, procura na sub-árvore esquerda
        }
        else if (no.documento.nome.compareTo(nomeDocumento) < 0) { // Verifica se o nome do documento procurado é maior que o nome do documento do nó que estamos
            comparacoes++;
            return buscar(no.direita, nomeDocumento); // Se for, procura na sub-árvore direita
        }
        else{ // Caso em que o número de caracteres dos documentos são iguais, mas os caracteres são diferentes
            comparacoes++;
            return buscar(no.direita, nomeDocumento); // Se for, procura na sub-árvore direita que foi definida para esse propósito por convenção
        }

    }

    public Set<String> SetDechaves() {
        Set<String> set = new HashSet<>();
        SetDeChaves(raiz, set); // Inicia a recursão a partir da raiz da árvore
        return set; // Retorna o conjunto contendo os documentos
    }

    // Método para retornar um conjunto contendo os documentos da árvore a partir de um nó
    private void SetDeChaves(No no, Set<String> set) {
        if (no == null) { // Verifica se o nó é nulo
            comparacoes++;
            return;
        }

        set.add(no.documento.nome); // Adiciona o nome do documento ao conjunto
        movimentacoes++;
        SetDeChaves(no.esquerda, set); // Chama recursivamente o método para a subárvore esquerda
        SetDeChaves(no.direita, set); // Chama recursivamente o método para a subárvore direita
    }

    public long getComparacoes() {
        return comparacoes;
    }
    public long getMovimentacoes() {
        return movimentacoes;
    }
}



