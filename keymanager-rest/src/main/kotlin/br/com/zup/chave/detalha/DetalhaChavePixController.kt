package br.com.zup.chave.detalha

import br.com.zup.DetalhaChavePixRequest
import br.com.zup.DetalhaChavePixServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory
import java.util.*

@Controller
class DetalhaChavePixController(val grpcClient: DetalhaChavePixServiceGrpc.DetalhaChavePixServiceBlockingStub) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Get("/api/v1/clientes/{clienteId}/pix/{pixId}")
    fun detalha(clienteId: UUID, pixId: UUID) : HttpResponse<Any> {

        logger.info("Pedido de detalhamento para a chave: $pixId, usando o sistema interno")

        val chavePix = DetalhaChavePixRequest.newBuilder()
            .setDados(DetalhaChavePixRequest.DadosPixInterno.newBuilder()
                .setClienteId(clienteId.toString())
                .setPixId(pixId.toString())
                .build())
            .build()

        val responseGrpc = grpcClient.detalhar(chavePix)

        val response = responseGrpc.toModel()

        return HttpResponse.ok(response)
    }
}