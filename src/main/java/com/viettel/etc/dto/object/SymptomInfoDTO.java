package com.viettel.etc.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SymptomInfoDTO {
    String appearanceDate;
    String appearanceTime;
    Integer heartbeat;
    Integer survivalIndex;
    Integer isSymptom;
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
