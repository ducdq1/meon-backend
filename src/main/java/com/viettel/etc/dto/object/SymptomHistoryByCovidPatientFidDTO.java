package com.viettel.etc.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymptomHistoryByCovidPatientFidDTO {
    String appearanceDate;
    String appearanceTime;
    Integer patientHealthDeclarationId;
    String symptom;
    Integer isSymptom;
}
