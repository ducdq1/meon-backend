package com.viettel.etc.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientInformationDTO {
    String fullName;
    Integer age;
    Integer gender;
    String phone;
    String identification;
    String address;
}
