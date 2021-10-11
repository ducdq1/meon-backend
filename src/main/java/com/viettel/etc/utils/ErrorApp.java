package com.viettel.etc.utils;

public enum ErrorApp {

    ERROR_INPUTPARAMS(400, FnCommon.getValueFileMess("err.data")),
    ERROR_TYPE_INDEX_NOT_EXIST(400, FnCommon.getValueFileMess("err.type.index.not.exist")),
    ERR_JSON_PARSE(400, FnCommon.getValueFileMess("err.json.parse")),
    ERROR_REGISTER(404, FnCommon.getValueFileMess("err.register")),
    ERR_USER_PERMISSION(403, FnCommon.getValueFileMess("err.user.permission")),
    ERR_DATA_PATIENT_NOT_EXIST(404, FnCommon.getValueFileMess("err.data.patient.not.exist")),
    ERROR_DATA_PHONE_NUMBER_EXIST(400, FnCommon.getValueFileMess("err.validate.phone.number.exist")),
    ERR_DATA_PATIENT_FULL_NAME(400, FnCommon.getValueFileMess("validate.patient.full_name.empty")),
    ERR_DATA_PATIENT_PHONE_NUMBER(400, FnCommon.getValueFileMess("validate.patient.phone_number.empty")),
    ERR_DATA_PATIENT_EMAIL_EXIST(400, FnCommon.getValueFileMess("validate.patient.email.empty")),
    ERR_DATA_PATIENT_INFO(400, FnCommon.getValueFileMess("validate.patient.empty")),
    ERR_DATA_PATIENT_RELATIVE_NOT_EXIST(404, FnCommon.getValueFileMess("err.data.patient_relative.not.exist")),
    ERROR_DATA_OTP_EXIST(400, FnCommon.getValueFileMess("err.validate.otp.exist")),
    ERROR_DATA_OTP_INVALID(400, FnCommon.getValueFileMess("err.validate.otp.invalid")),
    ERROR_DATA_OTP_EXPIRED(400, FnCommon.getValueFileMess("err.validate.otp.expired")),
    DELETE_PATIENT_RELATIVE_SUCCESS(200, FnCommon.getValueFileMess("message.delete.patient_relative.success")),
    ERR_PATIENT_RELATIONSHIP_NOT_EXIST(404, FnCommon.getValueFileMess("err.data.patient_relationship.not.exist")),
    ERROR_DATE_NULL(400, FnCommon.getValueFileMess("err.date.null")),
    ERR_DATA_HEALTH_INSURANCE_NOT_EXIST(404, FnCommon.getValueFileMess("err.data.health_insurance.not.exist")),
    ERR_DATA_HEALTH_INSURANCE_EXIST(400, FnCommon.getValueFileMess("err.data.health_insurance.exist")),
    ERR_DATA_HEALTH_INSURANCE_NUMBER_REQUIRE(400, FnCommon.getValueFileMess("err.data.health_insurance.require")),
    ERR_DATA_HEALTH_INSURANCE_NUMBER_VALIDATE(400, FnCommon.getValueFileMess("err.data.health_insurance.validate")),
    ERR_USER_NOT_PERMISSION(401, FnCommon.getValueFileMess("message.user.permission.denied")),
    ERR_IMAGE_FILE_INVALID(400, FnCommon.getValueFileMess("err.data.image.invalid")),
    ERR_DATA_MEDICAL_HEALTHCARE_PRESURGERY_INFO(400, FnCommon.getValueFileMess("validate.medicalhealthcarepresurgery.empty")),
    ERR_DATA_MEDICAL_HEALTHCARE_ALLERGY_INFO(400, FnCommon.getValueFileMess("validate.medicalhealthcareallergy.empty")),
    UNKNOW_ERROR(400, FnCommon.getValueFileMess("err.unknow")),
    ERR_DATA_HEALTHCARE_DRUG_NOT_EXIST(404, FnCommon.getValueFileMess("err.data.healthcar_drug.not.exist")),
    ERROR_INPUT_PARAMS_HEALTH_INSURANCE_NUMBER(404, FnCommon.getValueFileMess("err.data.healthinsurancenumber.format")),
    ERR_RELATIONSHIP_NOT_EXIST(404, FnCommon.getValueFileMess("err.data.relationship.not.exist")),
    ERR_REGIMEN_GROUP_NOT_EXIST(400, FnCommon.getValueFileMess("err.data.regimen_group.not.exist")),
    ERR_MEDICAL_HEALTHCARE_REGIMEN_NOT_EXIST(400, FnCommon.getValueFileMess("err.data.medical.healthcare.regimen.not.exist")),
    ERR_REGIMEN_DETAIL_NOT_EXIST(400, FnCommon.getValueFileMess("err.data.regimen.detail.not.exist")),
    ERR_REGIMEN_CREATE_FAIL(400, FnCommon.getValueFileMess("err.regimen.create.fail")),
    ERR_REGIMEN_UPDATE_FAIL(400, FnCommon.getValueFileMess("err.regimen.update.fail")),
    ERR_REGIMEN_DELETE_FAIL(400, FnCommon.getValueFileMess("err.regimen.delete.fail")),
    ERR_PATIENT_NOT_HAS_REGIMEN(400, FnCommon.getValueFileMess("err.patient.not_has.regimen")),
    ERR_REGIMEN_EXECUTED(400, FnCommon.getValueFileMess("err.regimen.executed")),
    ERROR_DETAIL_TYPE_NOT_EXIST(404,
            FnCommon.getValueFileMess("err.detail_type.not_exist")),
    ERR_DETAIL_NAME_ID_NOT_EXIST(404,  FnCommon.getValueFileMess("err.detail_name_id.not_exist")),
    ERR_REPEAT_STATUS_NOT_EXIST(404,  FnCommon.getValueFileMess("err.repeat_status.not_exist")),
    ERR_REPEAT_NUMBER_NOT_EXIST(404,  FnCommon.getValueFileMess("err.repeat_number.not_exist")),
    REGISTER_VIDEO_CALL_DEVICES_FALSE(400,  FnCommon.getValueFileMess("err.register_video_call_devices_false")),
    ERR_DOCTOR_HAVE_NO_PATIENT(400,  FnCommon.getValueFileMess("err.doctor_have_no_patient")),
    SUCCESS_CREATE_COVID_PATIENT(1, ""),
    FAIL_CREATE_COVID_PATIENT(400, ""),
    DOCTOR_IS_NOT_IN_HEALTH_FACILITY(400,""),
    ERROR_SAVE_DATA(400,""),
    ERROR_PATIENT_COVID_F_NOT_EXIST(400,"err.covid_patient_f.not_exist"),
    ERROR_DOCTOR_CONSULTANT_EMPTY(400,"err.doctor_consultant.empty"),
    ERROR_DOCTOR_CONSULTANT_DESC_EXCEED_MAX_LENGTH(400,"err.doctor_consultant.desc_exceed_max_length"),
    ERROR_PATIENT_COVID_F_ID_REQUIRED(400,"err.covid_patient_f.id_required"),
    ERROR_PATIENT_COVID_F_ID_NOT_EXIST(400,"err.covid_patient_f.id_not_exist"),
    ERROR_DOCTOR_ID_REQUIRED(400,"err.doctor.id_required"),
    ERROR_DOCTOR_ID_NOT_EXIST(400,"err.doctor.id_not_exist"),
    ERROR_DATA_SYMPTOM_RESULT_NOT_FOUND(404, ""),
    ERROR_OVER_TIME_TO_EDIT_SYMPTOM_RESULT(400, ""),
    ERROR_HEALTH_DECLARATION_HISTORY_NOT_FOUND(400,"error.heath_declaration_history.not_exist")
    ;

    private int code;
    private String description;

    private ErrorApp(int code, String description) {
        this.code = code;
        this.description = description;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

//	public String toStringJson() {
//		Map<String, Object> objectMap = new HashMap<>();
//		objectMap.put("code", code);
//		objectMap.put("description", description);
//		return FnCommon.convertObjectToStringJson(objectMap);
//	}
}
