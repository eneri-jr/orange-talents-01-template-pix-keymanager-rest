package br.com.zup.chave.remove

import br.com.zup.ChavePixRequest
import br.com.zup.RemoveChavePixServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import java.util.*

@Controller
class RemoveChavePixController(val grpcClient: RemoveChavePixServiceGrpc.RemoveChavePixServiceBlockingStub){

    @Delete("/api/v1/clientes/{clienteId}/pix/{pixId}")
    fun remove(clienteId: UUID, pixId: UUID) : HttpResponse<Any> {

        val chavePix = ChavePixRequest.newBuilder()
            .setPixId(pixId.toString())
            .setClientId(clienteId.toString())
            .build()

        grpcClient.remover(chavePix)

        return HttpResponse.ok()
    }

}