package com.light.paas.zk.web.model.node;

import com.light.paas.zk.web.model.VO;
import lombok.*;
import org.apache.zookeeper.data.ACL;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeInfoVO extends VO {
    private List<ACL> aclList;
}
