package by.itech.projectspring.repository;

import by.itech.projectspring.entity.DriverLicense;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverLicenseRepository extends CrudRepository<DriverLicense, UUID> {
    Optional<DriverLicense> findByLicenseUuid(UUID licenseUuid);
    void deleteByLicenseUuid(UUID licenseUuid);
}
