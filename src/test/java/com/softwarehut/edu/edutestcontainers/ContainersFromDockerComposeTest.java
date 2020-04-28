package com.softwarehut.edu.edutestcontainers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Testcontainers
public class ContainersFromDockerComposeTest {

    public static final int REDIS_PORT = 6379;
    public static final int POSTGRES_PORT = 5432;

    @Container
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/docker-compose/docker-compose-test.yml"))
                    .withExposedService("redis_1", REDIS_PORT)
                    .withExposedService("postgres_1", POSTGRES_PORT)
            .withLogConsumer("redis_1", new Slf4jLogConsumer(log))
            .withLogConsumer("postgres_1", new Slf4jLogConsumer(log))
                    ;

    @BeforeEach
    void setUp() {
        environment.start();
    }

    @Test
    void shouldTest() {
        Assertions.assertTrue(true );
    }

}
