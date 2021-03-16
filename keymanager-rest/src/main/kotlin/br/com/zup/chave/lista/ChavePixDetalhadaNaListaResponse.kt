package br.com.zup.chave.lista

import br.com.zup.chave.TipoChave
import br.com.zup.chave.TipoConta
import java.time.LocalDateTime
import java.util.*

data class ChavePixDetalhadaNaListaResponse(
    val pixId: UUID,
    val clienteId: UUID,
    val tipoChave: TipoChave,
    val valorChave: String,
    val tipoConta: TipoConta,
    val criadaEm: LocalDateTime
)