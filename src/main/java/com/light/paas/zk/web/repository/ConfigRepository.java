package com.light.paas.zk.web.repository;

import com.light.paas.zk.web.model.config.ConfigBO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ConfigRepository extends ReactiveCrudRepository<ConfigBO, Integer> {
}
