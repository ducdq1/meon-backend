package com.viettel.etc.dto.object;

import lombok.Data;

@Data
public class SymptomDTO {
    Integer patientHealthDeclarationId;
    String symptom;
    String appearanceTime;
    Integer isSymptom;
}
