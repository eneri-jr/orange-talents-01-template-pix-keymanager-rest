package br.com.zup.chave.registra

import br.com.zup.chave.TipoChave
import br.com.zup.chave.TipoConta
import br.com.zup.shared.validacoes.ValidPixKey
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@ValidPixKey
data class NovaChavePixRequestRest(
    @field:NotNull
    val tipoChave: TipoChave?,
    @field:Size(max = 77)
    val valorChave: String?,
    @field:NotNull
    val tipoConta: TipoConta?
)