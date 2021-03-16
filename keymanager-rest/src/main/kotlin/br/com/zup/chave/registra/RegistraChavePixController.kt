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
import java.util.*
import javax.validation.Valid

@Controller
@Validated
class RegistraChavePixController(val grpClient: RegistrarNovaChavePixServiceGrpc.RegistrarNovaChavePixServiceBlockingStub) {

    @Post("/api/v1/clientes/{clienteId}/pix")
    fun registra(clienteId: UUID, @Valid @Body request: NovaChavePixRequestRest): HttpResponse<Any> {

        val novaChave = NovaChavePixRequest.newBuilder()
            .setIdentificador(clienteId.toString())
            .setTipoChave(TipoChave.valueOf(request.tipoChave!!.name))
            .setValorChave(request.valorChave)
            .setTipoConta(TipoConta.valueOf(request.tipoConta!!.name))
            .build()

        val response = grpClient.registrar(novaChave)

        val location = HttpResponse.uri("/api/v1/clientes/$clienteId/pix/${response.pixId}")

        return HttpResponse.created(location)
    }
}