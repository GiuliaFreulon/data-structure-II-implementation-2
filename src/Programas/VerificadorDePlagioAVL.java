package Programas;

import Estruturas.ArvoreAVL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class VerificadorDePlagioAVL {
    long comparacoes = 0L;
    long movimentacoes = 0L;

    private ArvoreAVL arvore;
    public VerificadorDePlagioAVL(ArvoreAVL arvore) {
        this.comparacoes = 0;
        this.movimentacoes = 0;

        this.arvore = arvore;
    }

    // Método para inserir um documento na árvore
    public void inserir(String documento) {
        String nomeArquivo = "src/Documentos/" + documento + ".txt"; // Nome do arquivo a ser lido

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) { // Código para ler o arquivo
            String linha;
            LinkedList<String> palavras = new LinkedList<>();

            while ((linha = br.readLine()) != null) { // Percorre as linhas do documento
                comparacoes++;

                if (linha.isEmpty()) { // Encontrou uma linha em branco, termina de ler o documento
                    comparacoes++;
                    break;
                }

                String[] palavrasLinha = linha.split(" "); // Separa as palavras pelo reconhecimento de um espaço entre elas
                for (String palavra : palavrasLinha) { // Percorre as palavras da linha
                    palavra = palavra.trim().toLowerCase(); // Converte os caracteres da palavra para minúsculo e remove espaços em branco

                    if (!palavra.isEmpty()) {
                        comparacoes++;
                        palavras.add(palavra); // Adiciona cada palavra ao vetor de palavras do documento
                        movimentacoes++;
                    }
                }
            }

            arvore.inserirDocumento(documento, palavras); // Insere as palavras na árvore junto com o nome do documento
            comparacoes += arvore.getComparacoes();
            movimentacoes += arvore.getMovimentacoes();

        }
        catch (FileNotFoundException e) { // Trata uma exceção se o arquivo não for encontrado
            throw new RuntimeException(e);
        }
        catch (IOException e) { // Trata uma exceção se ocorrer um erro de leitura/escrita no arquivo
            throw new RuntimeException(e);
        }
    }

    // Método para verificar plágio entre documentos armazenados na árvore com um documento de análise, tendo uma sequência de palavras iguais consecutivas de tolerância
    public boolean verificarPlagio(String documentoDeAnalise, int tamanhoSequencia) {
        LinkedList<String> palavrasDocumentoDeAnalise = arvore.buscarDocumento(documentoDeAnalise); // Procura o documento a ser analisado na árvore
        comparacoes += arvore.getComparacoes();
        movimentacoes += arvore.getMovimentacoes();

        if (palavrasDocumentoDeAnalise == null) { // Documento de análise não encontrado na árvore
            comparacoes++;
            return false;
        }

        int n = palavrasDocumentoDeAnalise.size();
        movimentacoes++;
        for (int i = 0; i <= n - tamanhoSequencia; i++) { // Loop que itera pelas sequências de m palavras no documento de análise
            LinkedList<String> sequencia = new LinkedList<>(palavrasDocumentoDeAnalise.subList(i, i + tamanhoSequencia)); // Cria uma sub-lista para armazenar as sequências de m palavras do documento de análise

            for (String nomeArquivo : arvore.SetDechaves()) { // Percorre os documentos da árvore
                if (!nomeArquivo.equals(documentoDeAnalise)) { // Percorre apenas os documentos com nome diferente do de análise
                    comparacoes++;

                    LinkedList<String> palavrasOutroDocumento = arvore.buscarDocumento(nomeArquivo); // Cria uma lista ligada para armazenar as palavras do documento a ser comparado
                    comparacoes += arvore.getComparacoes();
                    movimentacoes += arvore.getMovimentacoes();

                    for (int j = 0; j <= palavrasOutroDocumento.size() - tamanhoSequencia; j++) { // Loop que itera pelas sequências de m palavras no documento comparado
                        LinkedList<String> sequenciaOutroDocumento = new LinkedList<>(palavrasOutroDocumento.subList(j, j + tamanhoSequencia)); // Cria uma sub-lista para armazenar as sequências de m palavras do documento comparado

                        if (sequencia.equals(sequenciaOutroDocumento)) { // Caso encontre uma sequência de m palavras idêntica nos dois documentos
                            comparacoes++;
                            System.out.println("Documento: " + nomeArquivo + " - Sequência plagiada: " + sequencia);
                            return true; // Plágio detectado
                        }
                    }
                }
            }
        }

        return false; // Nenhum plágio detectado
    }

    public long getComparacoes() {
        return comparacoes;
    }
    public long getMovimentacoes() {
        return movimentacoes;
    }
}

