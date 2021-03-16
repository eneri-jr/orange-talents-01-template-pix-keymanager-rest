package br.com.zup.chave.lista

import br.com.zup.ClientePixRequest
import br.com.zup.ListaChavesPixServiceGrpc
import br.com.zup.chave.TipoChaveRest
import br.com.zup.chave.TipoContaRest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Controller
class ListaChavesPixController(val grpcClient: ListaChavesPixServiceGrpc.ListaChavesPixServiceBlockingStub) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Get("/api/v1/clientes/{clienteId}/pix")
    fun lista(clienteId: UUID) : HttpResponse<Any> {

        logger.info("Pedido de listagem para as chaves do clienteId: $clienteId")

        val cliente = ClientePixRequest.newBuilder()
            .setIdentificador(clienteId.toString())
            .build()

        val responseGrpc = grpcClient.listar(cliente)

        val response = responseGrpc.chavePixList.map {
            responseGrpc.listar(it)
        }

        return HttpResponse.ok(response)

    }


}