package by.itech.projectspring.service;

import java.util.UUID;

public interface CarServiceInterface<T> {
    T get(UUID id);
    void delete(UUID id);
    void update(T t);
    T save (T t);
}
