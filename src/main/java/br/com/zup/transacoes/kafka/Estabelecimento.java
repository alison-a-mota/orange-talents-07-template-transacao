package br.com.zup.transacoes.kafka;

public class Estabelecimento {

    private String nome;
    private String cidade;
    private String endereco;

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    @Deprecated
    public Estabelecimento() {
    }
}
