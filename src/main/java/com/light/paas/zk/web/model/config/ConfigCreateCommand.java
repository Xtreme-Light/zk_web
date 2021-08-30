package com.light.paas.zk.web.model.config;

import com.light.paas.zk.web.model.Create;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConfigCreateCommand extends Create implements ConfigCommand {
    @NotEmpty(message = "名称不能为空")
    private String name;
    private List<String> tagList;
    private String description;
    private Integer timeout;
    private String zkAddress;
    private Integer sessionExpireMs;
}
