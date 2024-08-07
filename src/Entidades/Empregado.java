package Entidades;

public class Empregado {
    int id;
    double salario;
    String nome;

    public Empregado(int id, double salario, String nome){
        this.id = id;
        this.salario = salario;
        this.nome = nome;
    }

    @Override
    public String toString() {
        String salarioString = String.format("%.2f", salario);
        return"{| ID: " + id + " " +
               "| Nome: " + nome +
               "| Salário: " + salarioString +
               " }";
    }

}
