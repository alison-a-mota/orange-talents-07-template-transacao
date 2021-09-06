package br.com.zup.transacoes.transacao;

import br.com.zup.transacoes.compartilhado.seguranca.HashCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/transacao")
public class TransacaoController {

    private final TransacaoRepository transacaoRepository;
    private final HashCode hashCode;

    public TransacaoController(TransacaoRepository transacaoRepository, HashCode hashCode) {
        this.transacaoRepository = transacaoRepository;
        this.hashCode = hashCode;
    }

    @GetMapping("/cartao/{cartaoId}")
    public ResponseEntity<Stream> listaUltimasDez(@PathVariable String cartaoId) throws NoSuchAlgorithmException {

        var lista = transacaoRepository.findLast10ByHashCartao(hashCode.gerarHash(cartaoId));

        if (lista.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(lista.stream().map(TransacaoResponse::new));
    }
}
