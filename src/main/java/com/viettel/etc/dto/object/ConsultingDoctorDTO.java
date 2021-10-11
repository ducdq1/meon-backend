package com.viettel.etc.dto.object;

import lombok.Data;

import java.util.Date;

@Data
public class ConsultingDoctorDTO {
    Integer patientHealthDeclarationId;
    Date appearanceDate;
    String content;
    Integer levelOfRisk;
    Integer doctorConsultant;
    String doctorName;
}
