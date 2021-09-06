package br.com.zup.transacoes.kafka;


import br.com.zup.transacoes.compartilhado.seguranca.HashCode;
import br.com.zup.transacoes.transacao.Transacao;
import br.com.zup.transacoes.transacao.TransacaoEstabelecimento;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class ResponseTransacao {

    private String id;
    private BigDecimal valor;
    private LocalDateTime efetivadaEm;

    private Estabelecimento estabelecimento;
    private Cartao cartao;

    public ResponseTransacao(String id, BigDecimal valor, LocalDateTime efetivadaEm,
                             Estabelecimento estabelecimentoResponse, Cartao cartaoResponse) {
        this.id = id;
        this.valor = valor;
        this.efetivadaEm = efetivadaEm;
        this.estabelecimento = estabelecimentoResponse;
        this.cartao = cartaoResponse;
    }

    public Transacao toModel(HashCode hashCode) throws NoSuchAlgorithmException {
        TransacaoEstabelecimento transacaoEstabelecimento = new TransacaoEstabelecimento(this.estabelecimento.getNome(),
                this.estabelecimento.getCidade(), this.estabelecimento.getEndereco());

        return new Transacao(id, valor, efetivadaEm, cartao.getId(), cartao.getEmail(), transacaoEstabelecimento, hashCode);
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Deprecated
    public ResponseTransacao() {
    }
}
