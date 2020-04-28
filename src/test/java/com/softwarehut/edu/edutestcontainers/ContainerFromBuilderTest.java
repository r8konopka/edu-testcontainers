package com.softwarehut.edu.edutestcontainers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainerFromBuilderTest {

    @Container
    public GenericContainer nginx = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder ->
                            builder
                                    .from("alpine:3.2")
                                    .run("apk add --update nginx")
                                    .cmd("nginx", "-g", "daemon off;")
                                    .build()))
            .withExposedPorts(80);

    @BeforeEach
    void setUp() {
        nginx.start();
    }

    @Test
    void shouldTest() {

        Assertions.assertTrue(true );
    }

}
