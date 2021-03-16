package br.com.zup.chave.detalha

import br.com.zup.DetalhaChavePixRequest
import br.com.zup.DetalhaChavePixServiceGrpc
import br.com.zup.chave.TipoChave
import br.com.zup.chave.TipoConta
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Controller
class DetalhaChavePixController(val grpcClient: DetalhaChavePixServiceGrpc.DetalhaChavePixServiceBlockingStub) {

    @Get("/api/v1/clientes/{clienteId}/pix/{pixId}")
    fun detalha(clienteId: UUID, pixId: UUID) : HttpResponse<Any> {

        val chavePix = DetalhaChavePixRequest.newBuilder()
            .setDados(DetalhaChavePixRequest.DadosPixInterno.newBuilder()
                .setClienteId(clienteId.toString())
                .setPixId(pixId.toString())
                .build())
            .build()

        val responseGrpc = grpcClient.detalhar(chavePix)

        val response = ChavePixDetalhadaResponse(UUID.fromString(responseGrpc.pixId),
            UUID.fromString(responseGrpc.clientId),
            TipoChave.valueOf(responseGrpc.tipoChave),
            responseGrpc.valorChave,
            ContaAssociada(responseGrpc.conta.nome,
                responseGrpc.conta.cpf,
                responseGrpc.conta.instituicao,
                responseGrpc.conta.agencia,
                responseGrpc.conta.conta,
                TipoConta.valueOf(responseGrpc.conta.tipo)
                ),
            responseGrpc.registradaEm.let {
                LocalDateTime.ofEpochSecond(
                    it.seconds,
                    it.nanos,
                    ZoneOffset.UTC
                )
            })

        println(response)

        return HttpResponse.ok(response)
    }
}