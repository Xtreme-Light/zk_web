package com.light.paas.zk.web.model.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.light.paas.zk.web.model.BO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("config")
public class ConfigBO extends BO {
    @Id
    private Integer id;
    private String name;
    private List<String> tagList;
    private String description;
    private Integer timeout;
    private String zkAddress;
    private Integer sessionExpireMs;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant createdDate;
}
