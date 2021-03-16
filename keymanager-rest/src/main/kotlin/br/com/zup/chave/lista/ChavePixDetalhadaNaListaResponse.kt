package br.com.zup.chave.lista

import br.com.zup.ListaDeChavesPixResponse
import br.com.zup.chave.TipoChaveRest
import br.com.zup.chave.TipoContaRest
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class ChavePixDetalhadaNaListaResponse(
    val pixId: UUID,
    val clienteId: UUID,
    val tipoChaveRest: TipoChaveRest,
    val valorChave: String,
    val tipoContaRest: TipoContaRest,
    val criadaEm: LocalDateTime
)

fun ListaDeChavesPixResponse.listar(chaveResponse : ListaDeChavesPixResponse.ChavePix) : ChavePixDetalhadaNaListaResponse {
    return ChavePixDetalhadaNaListaResponse(
        UUID.fromString(chaveResponse.pixId),
        UUID.fromString(chaveResponse.clienteId),
        TipoChaveRest.valueOf(chaveResponse.tipoChave),
        chaveResponse.valorChave,
        TipoContaRest.valueOf(chaveResponse.tipoConta),
        chaveResponse.criadaEm.let {
            LocalDateTime.ofEpochSecond(
                it.seconds,
                it.nanos,
                ZoneOffset.UTC
            )
        })
}