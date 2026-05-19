package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * Test application for MySQL profile.
 */
@SpringBootApplication
public class MysqlTestApplication {

    @ServiceConnection
    static MySQLContainer<?> mysql =
            new MySQLContainer<>(DockerImageName.parse("mysql:9.6"));

    public static void main(String[] args) {
        SpringApplication.run(MysqlTestApplication.class, args);
    }
}