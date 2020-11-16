package by.itech.projectspring.config;


import by.itech.projectspring.service.Element;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "by.itech.projectspring")
public class Configuration<T> {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ReadWriteLock readWriteLock() {
        return new ReentrantReadWriteLock();
    }

    @Bean
    public Map<UUID, Element<T>> cacheMap() {
        return new HashMap<>();
    }
}