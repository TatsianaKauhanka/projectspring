package by.itech.projectspring.service;

import java.util.UUID;

public interface UserServiceI<T> {
    T get(UUID id);
    void delete(UUID id);
    void update(T t);
    T save (T t);
    void addCar(UUID id, UUID vin);
    void deleteCar(UUID id, UUID vin);
    void addLicense(UUID id, UUID licenseId);
    void deleteLicense(UUID id, UUID licenseId);
}