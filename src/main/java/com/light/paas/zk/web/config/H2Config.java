package com.light.paas.zk.web.config;

import com.light.paas.zk.web.model.config.ConfigBO;
import com.light.paas.zk.web.model.node.NodeInfoBO;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Configuration
public class H2Config {
    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ByteArrayResource(("CREATE SEQUENCE config_primary_key;" +
                "CREATE SEQUENCE node_info_primary_key;"+
                "DROP TABLE IF EXISTS config;"+
                "CREATE TABLE config (" +
                "       id INT PRIMARY KEY," +
                "       name VARCHAR(100) NOT NULL," +
                "       tag_list VARCHAR(100)," +
                "       description VARCHAR(100)," +
                "       timeout INT," +
                "       zk_address VARCHAR(100)," +
                "       session_expire_ms INT," +
                "       created_date TIMESTAMP);" +
                "CREATE TABLE node_info (" +
                "id INT PRIMARY KEY," +
                "absolute_path VARCHAR(255) NOT NULL," +
                "create_date TIMESTAMP);" +
                "")
                .getBytes())));

        return initializer;
    }
    @Bean
    BeforeConvertCallback<ConfigBO> configBOIdGeneratingCallback(DatabaseClient databaseClient) {

        return (customer, sqlIdentifier) -> {

            if (customer.getId() == null) {
                return databaseClient.sql("SELECT config_primary_key.nextval") //
                        .map(row -> row.get(0, Long.class)) //
                        .first() //
                        .map(k->{
                            customer.setId(k.intValue());
                            customer.setCreatedDate(Instant.now());
                            return customer;
                        });
            }

            return Mono.just(customer);
        };
    }
    @Bean
    BeforeConvertCallback<NodeInfoBO> nodeInfoIdGeneratingCallback(DatabaseClient databaseClient) {

        return (customer, sqlIdentifier) -> {

            if (customer.getId() == null) {
                return databaseClient.sql("SELECT node_info_primary_key.nextval") //
                        .map(row -> row.get(0, Long.class)) //
                        .first() //
                        .map(k->{
                            customer.setId(k.intValue());
                            customer.setCreateDate(Instant.now());
                            return customer;
                        });
            }

            return Mono.just(customer);
        };
    }
}
