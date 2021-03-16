package br.com.zup.shared.factory

import br.com.zup.RegistrarNovaChavePixServiceGrpc
import br.com.zup.RemoveChavePixServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientFactory {

    @Singleton
    fun RegistraChavePixClientStub(@GrpcChannel("keymanager-grpc") channel: ManagedChannel) : RegistrarNovaChavePixServiceGrpc.RegistrarNovaChavePixServiceBlockingStub? {
        return RegistrarNovaChavePixServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun RemoveChavePixClientStub(@GrpcChannel("keymanager-grpc") channel: ManagedChannel) : RemoveChavePixServiceGrpc.RemoveChavePixServiceBlockingStub? {
        return RemoveChavePixServiceGrpc.newBlockingStub(channel)
    }
}