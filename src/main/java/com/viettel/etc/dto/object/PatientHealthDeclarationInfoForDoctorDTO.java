package com.viettel.etc.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientHealthDeclarationInfoForDoctorDTO {
    Integer patientHealthDeclarationId;
    Date appearanceDate;
    Integer heartbeat;
    Integer breathing;
    String spo2Name;
    Integer maxBloodPressure;
    Integer minBloodPressure;
    String symptom;
    String backgroundDisease;
    Integer isPregnant;
    Integer isVaccination;
    Integer isTestCovid;
    Integer testType;
    Date testDate;
    Integer testResult;
    Integer levelOfRisk;
    Integer doctorConsultant;
    String contentConsulting;
    Integer emergencySituation;
}

