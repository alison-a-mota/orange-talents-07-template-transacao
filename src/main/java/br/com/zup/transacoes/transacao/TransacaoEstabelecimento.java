package br.com.zup.transacoes.transacao;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TransacaoEstabelecimento {

    @Column(name = "nome_estabelecimento")
    private String nome;
    private String cidade;
    private String endereco;

    public TransacaoEstabelecimento(String nome, String cidade, String endereco) {
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
    public TransacaoEstabelecimento() {
    }
}
