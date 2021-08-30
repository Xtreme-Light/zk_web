package com.light.paas.zk.web.service;

import com.light.paas.zk.web.model.config.ConfigBO;
import com.light.paas.zk.web.model.config.ConfigCreateCommand;
import com.light.paas.zk.web.model.config.ConfigVO;
import com.light.paas.zk.web.repository.ConfigRepository;
import com.light.paas.zk.web.response.MultiResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.Instant;


@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigService {

    private final @NonNull ConfigRepository configRepository;
    @Transactional
    public Mono<Void> addConfig(Mono<ConfigCreateCommand> configCommand) {
        return configCommand.flatMap($v -> {
            ConfigBO configBO = new ConfigBO();
            BeanUtils.copyProperties($v,configBO);
            configBO.setCreatedDate(Instant.now());
            return configRepository.save(configBO);
        }).thenEmpty(Mono.empty()).log();
    }
    public Mono<Void> removeConfig(@Validated Mono<Integer> configCommand) {
        return configCommand.doOnNext(configRepository::deleteById).thenEmpty(Mono.empty());
    }
    public Mono<MultiResponse<ConfigVO>> listConfig() {
        return configRepository.findAll().map($v->{
            ConfigVO configVO = new ConfigVO();
            BeanUtils.copyProperties($v,configVO);
            return configVO;
        }).collectList().map(MultiResponse::of).log();
    }
}
