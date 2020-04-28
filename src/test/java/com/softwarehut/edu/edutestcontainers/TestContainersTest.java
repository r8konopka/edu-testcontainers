package com.softwarehut.edu.edutestcontainers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.extern.slf4j.Slf4j;

@Testcontainers
@Slf4j
public class TestContainersTest {

    @Container
    public static GenericContainer redis =
            new GenericContainer("redis:3.0.2")
                    .withExposedPorts(6379)
                    .withLogConsumer(new Slf4jLogConsumer(log))
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

    @Test
    void shouldSaveInRedis2() {
        RedisBackedCache cache = buildRedisBackedCache();

        cache.put(CacheKey.COMPANY.name(), "softwareHut");
        String company = cache.get(CacheKey.COMPANY.name());

        Assertions.assertEquals("zonifero", company);
    }

    RedisBackedCache buildRedisBackedCache() {
        return new RedisBackedCache(redis.getContainerIpAddress(), redis.getFirstMappedPort());
    }

}
