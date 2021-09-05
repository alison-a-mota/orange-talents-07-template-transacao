package br.com.zup.transacoes.compartilhado.kafka;

import javax.validation.constraints.Email;

public class ResponseCartao {

    private String id;
    @Email
    private String email;

    public ResponseCartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
