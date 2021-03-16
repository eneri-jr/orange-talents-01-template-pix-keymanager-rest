package br.com.zup.chave.detalha

import br.com.zup.chave.TipoChave
import br.com.zup.chave.TipoConta
import java.time.LocalDateTime
import java.util.*

data class ChavePixDetalhadaResponse(
    val pixId: UUID,
    val clienteId: UUID,
    val tipoChave: TipoChave,
    val valorChave: String,
    val conta: ContaAssociada,
    val registradaEm: LocalDateTime

)

data class ContaAssociada(
    val nomeTitular: String,
    val cpfTitular: String,
    val instituicao: String,
    val agencia: String,
    val conta: String,
    val tipoConta: TipoConta
)