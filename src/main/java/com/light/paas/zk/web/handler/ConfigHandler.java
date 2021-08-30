package com.light.paas.zk.web.handler;

import com.light.paas.zk.web.exception.BizErrorEnum;
import com.light.paas.zk.web.model.config.ConfigCreateCommand;
import com.light.paas.zk.web.model.config.ConfigVO;
import com.light.paas.zk.web.response.MultiResponse;
import com.light.paas.zk.web.response.SingleResponse;
import com.light.paas.zk.web.service.ConfigService;
import com.light.paas.zk.web.util.ValidatorUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ConfigHandler {
    private final ConfigService configService;

    public ConfigHandler(ConfigService configService) {
        this.configService = configService;
    }

    public @NotNull Mono<ServerResponse> addConfig(ServerRequest request) {
        return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(
                        configService.addConfig(request.bodyToMono(ConfigCreateCommand.class))
                                .doOnNext(ValidatorUtils::validateFast),
                        Void.class))
                .onErrorResume(e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(SingleResponse.buildFailure(BizErrorEnum.DEFAULT_BIZ_ERROR.getErrorCode(), e.getMessage()))
                );
    }

    public @NotNull Mono<ServerResponse> removeConfig(ServerRequest request) {
        return ServerResponse.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(
                        configService.removeConfig(Mono.just(Integer.valueOf(request.pathVariable("id"))))
                                .doOnNext(ValidatorUtils::validateFast),
                        Void.class))
                .onErrorResume(e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(SingleResponse.buildFailure(BizErrorEnum.DEFAULT_BIZ_ERROR.getErrorCode(), e.getMessage()))
                );
    }

    public @NotNull Mono<ServerResponse> listConfig(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(configService.listConfig(),
                        new ParameterizedTypeReference<MultiResponse<ConfigVO>>() {
                }))
                .onErrorResume(e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(SingleResponse.buildFailure(BizErrorEnum.DEFAULT_BIZ_ERROR.getErrorCode(), e.getMessage()))
                );
    }

}
