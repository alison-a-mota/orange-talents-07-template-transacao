package br.com.zup.transacoes.transacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findFirst10ByHashCartaoOrderByEfetivadaEmDesc(byte[] cartaoId);
}

