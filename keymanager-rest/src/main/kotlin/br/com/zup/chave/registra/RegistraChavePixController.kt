package br.com.zup.chave.registra

import br.com.zup.NovaChavePixRequest
import br.com.zup.RegistrarNovaChavePixServiceGrpc
import br.com.zup.TipoChave
import br.com.zup.TipoConta
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.util.*
import javax.validation.Valid

@Controller
@Validated
class RegistraChavePixController(val grpClient: RegistrarNovaChavePixServiceGrpc.RegistrarNovaChavePixServiceBlockingStub) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post("/api/v1/clientes/{clienteId}/pix")
    fun registra(clienteId: UUID, @Valid @Body request: NovaChavePixRequestRest): HttpResponse<Any> {

        logger.info("Registro da chave: ${request.valorChave}, referente a conta do cliente: ${clienteId}")

        val novaChave = request.toModel(clienteId)

        val response = grpClient.registrar(novaChave)

        logger.info("Chave Pix cadastrada com sucesso no sistema do Itaú e Bcb")

        val location = HttpResponse.uri("/api/v1/clientes/$clienteId/pix/${response.pixId}")

        return HttpResponse.created(location)
    }
}