package com.light.paas.zk.web.service;

import com.light.paas.zk.web.repository.ConfigRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZkOperatorService {
    private final @NonNull ConfigRepository configRepository;


/*    public Mono<SingleResponse<NodeInfoVO>> getNodeInfo(Mono<NodeInfoRetrieveCommand> subscriber) {
        return subscriber.flatMap($v -> {
            Mono<ConfigBO> byId = configRepository.findById($v.getZkId());
            try {
                Stat stat = client.checkExists().forPath($v.getAbsolutePath());
                if (stat == null) {
                    return Mono.error(ExceptionFactory.bizException(BizErrorEnum.TARGET_NODE_NOT_EXISTS));
                }
                String nodeData = new String(client.getData().forPath($v.getAbsolutePath()), StandardCharsets.UTF_8);
                log.info("获取到{}的数据为{}",$v.getZkId(),nodeData);
                List<ACL> acls = client.getACL().forPath($v.getAbsolutePath());
                return Mono.just(SingleResponse.of(NodeInfoVO.builder().aclList(acls).build()));
            } catch (Exception e) {
                return Mono.error(ExceptionFactory.bizException(e.getMessage(), e));
            }
        });
    }*/
}
