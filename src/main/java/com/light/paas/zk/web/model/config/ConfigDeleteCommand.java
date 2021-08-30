package com.light.paas.zk.web.model.config;

import com.light.paas.zk.web.model.Delete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigDeleteCommand extends Delete implements ConfigCommand {

    private Integer id;
}
