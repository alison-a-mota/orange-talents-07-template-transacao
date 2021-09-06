package br.com.zup.transacoes.kafka;

public class Cartao {

    private String id;
    private String email;

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Deprecated
    public Cartao() {
    }
}
