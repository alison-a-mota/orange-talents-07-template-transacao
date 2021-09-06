package br.com.zup.transacoes.transacao;

import br.com.zup.transacoes.compartilhado.seguranca.HashCode;
import org.springframework.security.crypto.encrypt.Encryptors;

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

    /**
     * @param hashCode Este construtor recebe um cartão em formato de String, converte em HashCode
     *                 para comparações e validações e criptografa para usar os dados quando necessário.
     */
    public Transacao(String idTransacaoRecebida, BigDecimal valor, LocalDateTime efetivadaEm, String numeroCartao,
                     String emailUsuarioLogado, TransacaoEstabelecimento estabelecimento,
                     HashCode hashCode) throws NoSuchAlgorithmException {
        this.idTransacaoRecebida = idTransacaoRecebida;
        this.valor = valor;
        this.efetivadaEm = efetivadaEm;
        this.numeroCartao = Encryptors.text("abcabc", "cbacba").encrypt(numeroCartao);
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