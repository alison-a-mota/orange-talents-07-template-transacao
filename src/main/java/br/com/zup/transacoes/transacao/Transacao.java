package br.com.zup.transacoes.transacao;

import br.com.zup.transacoes.compartilhado.seguranca.HashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;
    private String idTransacaoRecebida;
    private BigDecimal valor;
    private LocalDateTime efetivadaEm;
    private String numeroCartao;
    private byte[] hashCartao;
    private String emailUsuarioLogado;

    @Embedded
    private TransacaoEstabelecimento estabelecimento;

    public Transacao(String idTransacaoRecebida, BigDecimal valor, LocalDateTime efetivadaEm, String numeroCartao,
                     String emailUsuarioLogado, TransacaoEstabelecimento estabelecimento,
                     HashCode hashCode) throws NoSuchAlgorithmException {
        this.idTransacaoRecebida = idTransacaoRecebida;
        this.valor = valor;
        this.efetivadaEm = efetivadaEm;
        this.numeroCartao = numeroCartao;
        this.hashCartao = hashCode.gerarHash(numeroCartao);
        this.emailUsuarioLogado = emailUsuarioLogado;
        this.estabelecimento = estabelecimento;
    }

    public String getIdTransacaoRecebida() {
        return idTransacaoRecebida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public String getEmailUsuarioLogado() {
        return emailUsuarioLogado;
    }

    public TransacaoEstabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    @Deprecated
    public Transacao() {
    }
}