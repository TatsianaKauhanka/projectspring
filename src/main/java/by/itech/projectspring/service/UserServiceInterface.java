package by.itech.projectspring.service;

import java.util.UUID;

public interface UserServiceInterface<T> {
    T get(UUID id);
    void delete(UUID id);
    void update(T t);
    T save (T t);
    void addCar(T t);
    void deleteCar(T t);
}
