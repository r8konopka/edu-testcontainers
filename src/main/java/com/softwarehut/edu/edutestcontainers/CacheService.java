package com.softwarehut.edu.edutestcontainers;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {

    private final RedisTemplate<String, Object> template;

    public CacheService(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    void add() {
        Company company = new Company(1l, "SoftwareHut", "Software company");
        add(company);
    }

    void add(Company company) {
        System.out.println("Saving company in Redis");
        template.opsForList().rightPush(CacheKey.COMPANY.name(), company);
    }

    List list(String key) {
        return template.opsForList().range(key, 0, 10);
    }

    void get(String key) {
        template.opsForList().leftPop(key);
    }
}
