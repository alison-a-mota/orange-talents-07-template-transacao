package br.com.zup.transacoes.kafka;

import br.com.zup.transacoes.compartilhado.seguranca.HashCode;
import br.com.zup.transacoes.transacao.Transacao;
import br.com.zup.transacoes.transacao.TransacaoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Component
public class ListenerTransacao {

    private final TransacaoRepository transacaoRepository;
    private final HashCode hashCode;


    public ListenerTransacao(TransacaoRepository transacaoRepository, HashCode hashCode) {
        this.transacaoRepository = transacaoRepository;
        this.hashCode = hashCode;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void listenTransacoes(ResponseTransacao responseTransacao) throws NoSuchAlgorithmException {

        System.out.println(">>>>>>>>>>>>>>>> INICIANDO >>>>>>>>>>>>>>>>>>");

        Transacao transacao = responseTransacao.toModel(hashCode);
        transacaoRepository.save(transacao);

        System.out.println("_____________________________________________________");
        System.out.println("Transação com ID " + transacao.getIdTransacaoRecebida() + " recebida.");
        System.out.println("_____________________________________________________");

    }
}