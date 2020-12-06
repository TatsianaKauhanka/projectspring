package by.itech.projectspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class DriverLicenseDTO {
    @JsonProperty
    private String state;
    @JsonProperty
    private int validDays;
    @JsonProperty
    private int validYears;
    @JsonProperty
    private String licenseType;
    @JsonProperty
    private UUID licenseUuid;
}
