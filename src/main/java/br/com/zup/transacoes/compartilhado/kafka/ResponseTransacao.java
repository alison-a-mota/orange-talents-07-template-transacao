package br.com.zup.transacoes.compartilhado.kafka;


import br.com.zup.transacoes.transacao.Estabelecimento;
import br.com.zup.transacoes.transacao.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResponseTransacao {

    @NotBlank
    private String id;
    @NotNull
    private BigDecimal valor;
    @NotBlank
    private LocalDateTime efetivadaEm;

    @NotNull
    private ResponseEstabelecimento estabelecimentoResponse;
    @NotNull
    private ResponseCartao cartaoResponse;

    public ResponseTransacao(String id, BigDecimal valor, LocalDateTime efetivadaEm,
                             ResponseEstabelecimento estabelecimentoResponse, ResponseCartao cartaoResponse) {
        this.id = id;
        this.valor = valor;
        this.efetivadaEm = efetivadaEm;
        this.estabelecimentoResponse = estabelecimentoResponse;
        this.cartaoResponse = cartaoResponse;
    }

    public Transacao toModel() {
        Estabelecimento estabelecimento = new Estabelecimento(estabelecimentoResponse.getNome(),
                estabelecimentoResponse.getCidade(), estabelecimentoResponse.getEndereco());

        return new Transacao(id, valor, efetivadaEm, cartaoResponse.getId(), cartaoResponse.getEmail(), estabelecimento);
    }
}
