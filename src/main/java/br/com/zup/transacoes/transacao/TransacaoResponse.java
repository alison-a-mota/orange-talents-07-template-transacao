package br.com.zup.transacoes.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private final String emailUsuario;
    private final String nomeEstabelecimento;
    private final BigDecimal valor;
    private final String cidade;
    private final String endereco;
    private final LocalDateTime efetivadaEm;

    public TransacaoResponse(Transacao transacao) {
        this.emailUsuario = transacao.getEmailUsuarioLogado();
        this.nomeEstabelecimento = transacao.getEstabelecimento().getNome();
        this.valor = transacao.getValor();
        this.cidade = transacao.getEstabelecimento().getCidade();
        this.endereco = transacao.getEstabelecimento().getEndereco();
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
