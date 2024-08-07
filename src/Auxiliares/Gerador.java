package Auxiliares;

import Entidades.Empregado;
import java.util.Random;

public class Gerador {
    public class Geradores {

        public static Empregado geradorEmpregado(int chave){ // Gera um empregado com id = chave recebida, salario aleatório de 0 a 1000 e nome aleatório com 10 letras
            Random random = new Random();
            Empregado empregado = new Empregado(chave, random.nextDouble(0,1000), gerarStringAleatoria(10));

            return empregado;
        }

        public static String gerarStringAleatoria(int tamanho) { // Gera uma string aleatória com o tamanho recebido
            // Define os caracteres possíveis que podem estar na string
            String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

            Random random = new Random();
            StringBuilder sb = new StringBuilder(tamanho);

            for (int i = 0; i < tamanho; i++) {
                // Gera um índice aleatório para escolher um caractere da lista de caracteres
                int indiceAleatorio = random.nextInt(caracteres.length());
                // Adiciona o caractere escolhido à StringBuilder
                sb.append(caracteres.charAt(indiceAleatorio));
            }

            return sb.toString();
        }

    }
}
