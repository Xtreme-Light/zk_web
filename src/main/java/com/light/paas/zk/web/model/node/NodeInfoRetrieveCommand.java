package com.light.paas.zk.web.model.node;

import com.light.paas.zk.web.model.Retrieve;
import lombok.Data;

@Data
public class NodeInfoRetrieveCommand extends Retrieve {
    private Integer zkId;
    private String absolutePath;
}
