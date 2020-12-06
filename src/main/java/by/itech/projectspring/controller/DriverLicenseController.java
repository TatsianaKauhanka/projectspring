package by.itech.projectspring.controller;


import by.itech.projectspring.dto.DriverLicenseDTO;
import by.itech.projectspring.entity.DriverLicense;
import by.itech.projectspring.service.DriverLicenseService;
import by.itech.projectspring.utils.mapper.DriverLicenseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/license")
@RequiredArgsConstructor
public class DriverLicenseController {
    private final DriverLicenseMapper licenseMapper;
    private final DriverLicenseService licenseService;


    @PostMapping(value = "/{subtype}")
    protected DriverLicenseDTO doPost(@PathVariable String subtype, @RequestBody  DriverLicenseDTO licenseDTO) {
        log.debug("Saving driver license {}", licenseDTO);
        licenseDTO.setLicenseType(subtype);
        DriverLicense license = licenseMapper.licenseDTOtoLicenseEntity(licenseDTO);
        DriverLicense licenseFromDB = licenseService.save(license);
        DriverLicenseDTO licenseDTOFromDB = licenseMapper.licenseEntityToLicenseDTO(licenseFromDB);
        log.debug("Driver license {} has been saved to DB", licenseFromDB);
        return licenseDTOFromDB;
    }

    @GetMapping(value = "/{licenseUuid}")
    protected DriverLicenseDTO doGet(@PathVariable UUID licenseUuid){
        log.debug("Getting driver license number {}", licenseUuid );
        DriverLicense licenseFromDB = licenseService.get(licenseUuid);
        DriverLicenseDTO licenseDTOFromDB = licenseMapper.licenseEntityToLicenseDTO(licenseFromDB);
        log.debug("Driver license with number {} has been gotten from DB", licenseUuid);
        return licenseDTOFromDB;
    }

    @DeleteMapping(value = "/{licenseUuid}")
    protected void doDelete(@PathVariable UUID licenseUuid){
        log.debug("Deleting driver license number {}", licenseUuid);
        licenseService.delete(licenseUuid);
        log.debug("Driver license with number {} has been deleted from DB", licenseUuid);
    }

    @PutMapping(value = "/{subtype}/{licenseUuid}")
    protected void doPut(@PathVariable UUID licenseUuid, @PathVariable String subtype,  @RequestBody DriverLicenseDTO licenseDTO){
        log.debug("Updating driver license number {}", licenseUuid);
        licenseDTO.setLicenseUuid(licenseUuid);
        licenseDTO.setLicenseType(subtype);
        DriverLicense licenseFromDTO = licenseMapper.licenseDTOtoLicenseEntity(licenseDTO);
        licenseService.update(licenseFromDTO);
        log.debug("Driver license {} has been updated", licenseUuid);
    }
}