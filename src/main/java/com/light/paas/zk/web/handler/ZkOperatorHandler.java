package com.light.paas.zk.web.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.light.paas.zk.web.exception.BizErrorEnum;
import com.light.paas.zk.web.model.node.NodeInfoRetrieveCommand;
import com.light.paas.zk.web.model.node.NodeInfoVO;
import com.light.paas.zk.web.response.SingleResponse;
import com.light.paas.zk.web.service.ZkOperatorService;
import com.light.paas.zk.web.util.ValidatorUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ZkOperatorHandler {
    private final ZkOperatorService zkOperatorService;
    private final ObjectMapper objectMapper;

    public ZkOperatorHandler(ZkOperatorService zkOperatorService,
                             ObjectMapper objectMapper) {
        this.zkOperatorService = zkOperatorService;
        this.objectMapper = objectMapper;
    }

    public @NotNull Mono<ServerResponse> getNodeInfo(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                BodyInserters.fromPublisher(
                        zkOperatorService.getNodeInfo(
                                Mono.just(objectMapper.convertValue(serverRequest.queryParams(), NodeInfoRetrieveCommand.class))
                                        .doOnNext(v->v.setZkId(Integer.valueOf(serverRequest.pathVariable("id"))))
                                        .doOnNext(ValidatorUtils::validateFast)
                        ),
                        new ParameterizedTypeReference<SingleResponse<NodeInfoVO>>(){}
                )
        ).onErrorResume(e -> ServerResponse.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(SingleResponse.buildFailure(BizErrorEnum.DEFAULT_BIZ_ERROR.getErrorCode(), e.getMessage()))
        );
    }
}
