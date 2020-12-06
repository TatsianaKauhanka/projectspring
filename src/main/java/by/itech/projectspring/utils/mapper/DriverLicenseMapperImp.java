package by.itech.projectspring.utils.mapper;

import by.itech.projectspring.dto.DriverLicenseDTO;
import by.itech.projectspring.entity.DriverLicense;
import by.itech.projectspring.entity.PermanentDriverLicense;
import by.itech.projectspring.entity.TemporaryDriverLicense;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DriverLicenseMapperImp implements DriverLicenseMapper{
    @Override
    public DriverLicenseDTO licenseEntityToLicenseDTO(DriverLicense license) {
        if(license == null){
            return null;
        }
        DriverLicenseDTO licenseDTO = new DriverLicenseDTO();
        licenseDTO.setLicenseType("common");
        licenseDTO.setState(license.getState());
        licenseDTO.setLicenseUuid(license.getLicenseUuid());

        if(license instanceof TemporaryDriverLicense){
            licenseDTO.setValidDays(((TemporaryDriverLicense) license).getValidDays());
            licenseDTO.setLicenseType("temporary");
        }
        if(license instanceof PermanentDriverLicense){
            licenseDTO.setValidYears(((PermanentDriverLicense) license).getValidYears());
            licenseDTO.setLicenseType("permanent");
        }
        return licenseDTO;
    }

    @Override
    public DriverLicense licenseDTOtoLicenseEntity(DriverLicenseDTO licenseDTO) {
        if (licenseDTO ==null){
            return null;
        }
        DriverLicense license = createLicense(licenseDTO);
        license.setState(licenseDTO.getState());
        license.setLicenseUuid(licenseDTO.getLicenseUuid());
        return license;

    }
    private DriverLicense createLicense(DriverLicenseDTO licenseDTO){
        if(licenseDTO.getLicenseType().equals("temporary")){
            TemporaryDriverLicense license = new TemporaryDriverLicense();
            license.setValidDays(licenseDTO.getValidDays());
            return license;
        }
        if(licenseDTO.getLicenseType().equals("permanent")){
            PermanentDriverLicense license = new PermanentDriverLicense();
            license.setValidYears(licenseDTO.getValidYears());
            return license;
        }
        else return new DriverLicense();
    }
}
