package br.com.zup.transacoes.compartilhado.kafka;

import br.com.zup.transacoes.transacao.Transacao;
import br.com.zup.transacoes.transacao.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerTransacao {

    private final TransacaoRepository transacaoRepository;

    public ListenerTransacao(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    Logger logger = LoggerFactory.getLogger(ListenerTransacao.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void listenTransacoes(ResponseTransacao responseTransacao){
        Transacao transacao = responseTransacao.toModel();
        transacaoRepository.save(transacao);

        logger.info("Transação salva. Id: " + transacao.getId());

    }
}