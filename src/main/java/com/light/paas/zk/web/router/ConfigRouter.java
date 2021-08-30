package com.light.paas.zk.web.router;

import com.light.paas.zk.web.handler.ConfigHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class ConfigRouter {
    @Bean
    public RouterFunction<ServerResponse> configRoute(ConfigHandler configHandler) {
        return RouterFunctions
                .route(POST("/config").and(accept(MediaType.APPLICATION_JSON)), configHandler::addConfig)
                .andRoute(DELETE("/config/{id}").and(accept(MediaType.APPLICATION_JSON)),configHandler::removeConfig)
                .andRoute(GET("/config").and(accept(MediaType.APPLICATION_JSON)),configHandler::listConfig);
    }
}
