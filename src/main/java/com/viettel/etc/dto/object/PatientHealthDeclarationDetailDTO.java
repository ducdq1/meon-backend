package com.viettel.etc.dto.object;

import lombok.Data;

import java.util.List;

@Data
public class PatientHealthDeclarationDetailDTO {
    String appearanceDate;
    String appearanceTime;
    Integer isSymptom;
    List<SymptomDetailDTO> symptom;
    Integer survivalIndex;
    Integer heartbeat;
    Integer breathing;
    Integer spo2;
    String spo2Code;
    String spo2Name;
    Integer maxBloodPressure;
    Integer minBloodPressure;
    Integer isBackgroundDisease;
    Integer isPregnant;
    Integer isVaccination;
    Integer isConsultant;

}
