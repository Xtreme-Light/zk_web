package hello.unit;

import com.light.paas.zk.web.ZkWebApplication;
import com.light.paas.zk.web.model.config.ConfigBO;
import com.light.paas.zk.web.repository.ConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.io.IOException;
import static org.assertj.core.api.Assertions.*;
@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(classes = ZkWebApplication.class ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigRepositoryIntegrationTests {
    @Autowired
    ConfigRepository configRepository;
    @Autowired
    DatabaseClient database;
    @Test
    void generatesIdOnInsert() throws IOException {

        ConfigBO dave = ConfigBO.builder()
                .name("test")
                .description("test")
                .zkAddress("test")
                .build();

        this.configRepository.save(dave) //
                .as(StepVerifier::create) //
                .assertNext(actual -> {
                    assertThat(dave.getId()).isNull(); // immutable before save
                    assertThat(actual.getId()).isNotNull(); // after save
                }).verifyComplete();
    }
}
