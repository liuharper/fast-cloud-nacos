package fast.cloud.nacos.grpc.example.service.server;

import fast.cloud.nacos.common.grpc.annoation.GRpcService;
import fast.cloud.nacos.grpc.example.grpc.GrpcTestServiceGrpc;
import fast.cloud.nacos.grpc.example.grpc.GrpcTestService_Request_String;
import fast.cloud.nacos.grpc.example.grpc.GrpcTestService_Response_String;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;

@GRpcService
public class GrpcTestServiceImpl extends GrpcTestServiceGrpc.GrpcTestServiceImplBase {
    @Value("${grpc.port}")
    private int port;

    @Override
    public void reqString(GrpcTestService_Request_String request,
                          StreamObserver<GrpcTestService_Response_String> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder()
                .setResult("success:" + name +"===" + "port:" + port)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void reqStringServerStream(GrpcTestService_Request_String request,
                                      StreamObserver<GrpcTestService_Response_String> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder().setResult("success_1:" + name).build());
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder().setResult("success_2:" + name).build());
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder().setResult("success_3:" + name).build());
        responseObserver.onCompleted();
    }
}