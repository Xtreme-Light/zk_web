package com.light.paas.zk.web.model.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.light.paas.zk.web.model.VO;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ConfigVO extends VO {
    private Integer id;
    private String name;
    private List<String> tagList;
    private String description;
    private Integer timeout;
    private String zkAddress;
    private Integer sessionExpireMs;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
}
