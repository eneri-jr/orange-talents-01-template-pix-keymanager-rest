package br.com.zup.chave.remove

import br.com.zup.ChavePixRequest
import br.com.zup.RemoveChavePixServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import org.slf4j.LoggerFactory
import java.util.*

@Controller
class RemoveChavePixController(val grpcClient: RemoveChavePixServiceGrpc.RemoveChavePixServiceBlockingStub){

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Delete("/api/v1/clientes/{clienteId}/pix/{pixId}")
    fun remove(clienteId: UUID, pixId: UUID) : HttpResponse<Any> {

        logger.info("Pedido de remoção para a chave: $pixId")

        val chavePix = ChavePixRequest.newBuilder()
            .setPixId(pixId.toString())
            .setClientId(clienteId.toString())
            .build()

        grpcClient.remover(chavePix)

        logger.info("Chave Pix removida com sucesso no sistema do Itaú e Bcb")

        return HttpResponse.ok()
    }

}