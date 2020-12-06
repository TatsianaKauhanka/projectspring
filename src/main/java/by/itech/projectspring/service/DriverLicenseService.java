package by.itech.projectspring.service;

import by.itech.projectspring.entity.*;
import by.itech.projectspring.repository.DriverLicenseRepository;
import by.itech.projectspring.utils.exceptions.DriverLicenseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverLicenseService implements DocumentInterface<DriverLicense> {
    private final DriverLicenseRepository licenseRepository;

    @Override
    public DriverLicense get(UUID licenceUuid) {
        log.debug("Getting driver license with number {} in licenseService", licenceUuid);
        DriverLicense licenseFromDB = licenseRepository.findByLicenseUuid(licenceUuid).orElseThrow(DriverLicenseNotFoundException::new);
        log.debug("Driver license with number {} has been gotten to licenseService", licenceUuid);
        return licenseFromDB;
    }

    @Transactional
    @Override
    public void delete(UUID licenseUuid) {
        log.debug("Deleting driver license with number {} in licenseService", licenseUuid);
        licenseRepository.deleteByLicenseUuid(licenseUuid);
        log.debug("Driver license {} has been deleted", licenseUuid);

    }

    @Override
    public void update(DriverLicense license) {
        log.debug("Updating driver license {} in licenseService", license.getLicenseUuid());
        if(license instanceof PermanentDriverLicense){
            PermanentDriverLicense licenseFromDB = (PermanentDriverLicense) get(license.getLicenseUuid());
            licenseFromDB.setValidYears(((PermanentDriverLicense) license).getValidYears());
        }
        if(license instanceof TemporaryDriverLicense) {
            TemporaryDriverLicense licenseFromDB = (TemporaryDriverLicense) get(license.getLicenseUuid());
            licenseFromDB.setValidDays(((TemporaryDriverLicense) license).getValidDays());
        }
        DriverLicense licenseFromDB = get(license.getLicenseUuid());
        licenseFromDB.setState(license.getState());
        licenseRepository.save(licenseFromDB);
        log.debug("Driver license  {} has been updated", license.getLicenseUuid());
    }

    @Override
    public DriverLicense save(DriverLicense license) {
        log.debug("Saving driver license {} in service", license);
        license.setLicenseUuid(UUID.randomUUID());
        DriverLicense licenseFromDB = licenseRepository.save(license);
        log.debug("Driver license with number {} has been saved",licenseFromDB.getLicenseUuid());
        return licenseFromDB;
    }
}
