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
public class ContainerFromDockerfileTest {

    @Container
    public GenericContainer redis = new GenericContainer(
            new ImageFromDockerfile()
                    .withFileFromString("folder/someFile.txt", "Content as String")
                    .withFileFromClasspath("test.txt", "redis-dockerfile/test-resource.txt")
                    .withFileFromClasspath("Dockerfile", "redis-dockerfile/Dockerfile"))
            .withExposedPorts(6379)
            .waitingFor(Wait.defaultWaitStrategy());

    @BeforeEach
    void setUp() {
        redis.start();
    }

    @Test
    void shouldSaveInRedis() {
        RedisBackedCache cache = buildRedisBackedCache();

        cache.put(CacheKey.COMPANY.name(), "zonifero");
        String company = cache.get(CacheKey.COMPANY.name());

        Assertions.assertEquals("zonifero", company);
    }

    RedisBackedCache buildRedisBackedCache() {
        return new RedisBackedCache(redis.getContainerIpAddress(), redis.getFirstMappedPort());
    }

}
