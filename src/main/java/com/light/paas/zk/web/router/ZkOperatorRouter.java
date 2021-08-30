package com.light.paas.zk.web.router;

import com.light.paas.zk.web.handler.ZkOperatorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ZkOperatorRouter {
    @Bean
    public RouterFunction<ServerResponse> zkOperatorRoute(ZkOperatorHandler zkOperatorHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/zk/{id}/node")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                zkOperatorHandler::getNodeInfo
        );
    }
}
