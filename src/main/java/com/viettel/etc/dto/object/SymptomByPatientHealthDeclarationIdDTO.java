package com.viettel.etc.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SymptomByPatientHealthDeclarationIdDTO {
    Integer patientHealthDeclarationId;
    Integer symptom;
}
