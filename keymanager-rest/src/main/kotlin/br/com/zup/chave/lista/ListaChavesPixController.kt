package br.com.zup.chave.lista

import br.com.zup.ClientePixRequest
import br.com.zup.ListaChavesPixServiceGrpc
import br.com.zup.chave.TipoChave
import br.com.zup.chave.TipoConta
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Controller
class ListaChavesPixController(val grpcClient: ListaChavesPixServiceGrpc.ListaChavesPixServiceBlockingStub) {

    @Get("/api/v1/clientes/{clienteId}/pix")
    fun lista(clienteId: UUID) : HttpResponse<Any> {

        val cliente = ClientePixRequest.newBuilder()
            .setIdentificador(clienteId.toString())
            .build()

        val responseGrpc = grpcClient.listar(cliente)

        val response = responseGrpc.chavePixList.map {
            ChavePixDetalhadaNaListaResponse(
                UUID.fromString(it.pixId),
                UUID.fromString(it.clienteId),
                TipoChave.valueOf(it.tipoChave),
                it.valorChave,
                TipoConta.valueOf(it.tipoConta),
                it.criadaEm.let {
                    LocalDateTime.ofEpochSecond(
                        it.seconds,
                        it.nanos,
                        ZoneOffset.UTC
                    )
                }
            )
        }

        println(response)

        return HttpResponse.ok(response)

    }


}