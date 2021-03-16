package br.com.zup.chave.detalha

import br.com.zup.DetalhamentoPixResponse
import br.com.zup.chave.TipoChaveRest
import br.com.zup.chave.TipoContaRest
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class ChavePixDetalhadaResponse(
    val pixId: UUID,
    val clienteId: UUID,
    val tipoChaveRest: TipoChaveRest,
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
    val tipoContaRest: TipoContaRest
)

fun DetalhamentoPixResponse.toModel() : ChavePixDetalhadaResponse {
    return ChavePixDetalhadaResponse(UUID.fromString(pixId),
        UUID.fromString(clientId),
        TipoChaveRest.valueOf(tipoChave),
        valorChave,
        ContaAssociada(conta.nome,
            conta.cpf,
            conta.instituicao,
            conta.agencia,
            conta.conta,
            TipoContaRest.valueOf(conta.tipo)
        ),
        registradaEm.let {
            LocalDateTime.ofEpochSecond(
                it.seconds,
                it.nanos,
                ZoneOffset.UTC
            )
        })
}