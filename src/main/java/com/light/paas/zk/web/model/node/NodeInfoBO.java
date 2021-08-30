package com.light.paas.zk.web.model.node;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.light.paas.zk.web.model.BO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("node_info")
@Data
public class NodeInfoBO extends BO {
    private Integer id;
    private String absolutePath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant createDate;
}
