package by.itech.projectspring.utils.mapper;


import by.itech.projectspring.dto.DriverLicenseDTO;
import by.itech.projectspring.entity.DriverLicense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverLicenseMapper {
    DriverLicenseDTO licenseEntityToLicenseDTO (DriverLicense license);
    DriverLicense licenseDTOtoLicenseEntity(DriverLicenseDTO licenseDTO);

}
