package br.com.zup.transacoes.transacao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;
    @NotBlank
    private String idTransacaoRecebida;
    @NotNull
    private BigDecimal valor;
    @NotBlank
    private LocalDateTime efetivadaEm;
    @NotBlank
    private String numeroCartao;
    @Email
    @NotBlank
    private String emailUsuarioLogado;

    @Embedded
    private Estabelecimento estabelecimento;

    public Transacao(String idTransacaoRecebida, BigDecimal valor, LocalDateTime efetivadaEm, String numeroCartao, String emailUsuarioLogado, Estabelecimento estabelecimento) {
        this.idTransacaoRecebida = idTransacaoRecebida;
        this.valor = valor;
        this.efetivadaEm = efetivadaEm;
        this.numeroCartao = numeroCartao;
        this.emailUsuarioLogado = emailUsuarioLogado;
        this.estabelecimento = estabelecimento;
    }

    public Long getId() {
        return id;
    }

    @Deprecated
    public Transacao() {
    }
}