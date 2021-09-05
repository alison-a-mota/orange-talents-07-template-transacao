package br.com.zup.transacoes.transacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Estabelecimento {

    @NotBlank
    private String nome;
    private String cidade;
    private String endereco;

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }
}
