package br.com.zup.chave.registra

import br.com.zup.NovaChavePixRequest
import br.com.zup.TipoChave
import br.com.zup.TipoConta
import br.com.zup.chave.TipoChaveRest
import br.com.zup.chave.TipoContaRest
import br.com.zup.shared.validacoes.ValidPixKey
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@ValidPixKey
data class NovaChavePixRequestRest(
    @field:NotNull
    val tipoChaveRest: TipoChaveRest?,
    @field:Size(max = 77)
    val valorChave: String?,
    @field:NotNull
    val tipoContaRest: TipoContaRest?
) {
    fun toModel(clienteId: UUID) : NovaChavePixRequest {
        return NovaChavePixRequest.newBuilder()
            .setIdentificador(clienteId.toString())
            .setTipoChave(TipoChave.valueOf(tipoChaveRest!!.name))
            .setValorChave(valorChave)
            .setTipoConta(TipoConta.valueOf(tipoContaRest!!.name))
            .build()
    }
}