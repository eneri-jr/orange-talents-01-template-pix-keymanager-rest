package br.com.zup.shared.handler

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import javax.inject.Singleton

@Singleton
class GlobalExceptionHandler : ExceptionHandler<StatusRuntimeException, HttpResponse<Any>> {

    override fun handle(request: HttpRequest<*>, exception: StatusRuntimeException): HttpResponse<Any> {

        val status = exception.status.code
        val description = exception.status.description

        val (httpStatus, mensagem) = when (status) {
            Status.ALREADY_EXISTS.code -> Pair(HttpStatus.UNPROCESSABLE_ENTITY, description)
            Status.NOT_FOUND.code -> Pair(HttpStatus.NOT_FOUND, description)
            Status.INVALID_ARGUMENT.code -> Pair(HttpStatus.BAD_REQUEST, description)
            Status.FAILED_PRECONDITION.code -> Pair(HttpStatus.BAD_REQUEST, description)
            else -> Pair(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível realizar a requisição")
        }

        return HttpResponse.status<JsonError>(httpStatus).body(JsonError(mensagem))
    }
}