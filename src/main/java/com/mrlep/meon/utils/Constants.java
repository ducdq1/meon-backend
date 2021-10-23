package com.mrlep.meon.utils;

import com.squareup.okhttp.MediaType;
import org.springframework.http.HttpStatus;

public class Constants {
    public static final String REQUEST_MAPPING_V1 = "/api/v1";
    public static final String F0_MANAGEMENT = "/f0-management";
    public static final String MOBILE = "/mobile";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType JSON_TOKEN = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    public static final Integer BOOKING_ORDER_ADVISORY = 4;
    public static final Integer BOOKING_ORDER_CHAT = 5;
    public static final Integer GROUP_ID_Diagnostic_Image = 2;
    public static final Integer LIMIT_DEFAULT = 5;
    public static final Integer PAGE_SIZE_DEFAULT = 20;
    public static final Integer START_RECORD_DEFAULT = 0;
    public static final Integer IS_NOT_SYNC = 0;
    public static final Integer IS_DELETE = 1;
    public static final Integer IS_NOT_DELETE = 0;
    public static final Integer IS_ACTIVE = 1;
    public static final Integer IS_SYNC = 1;
    public static final Boolean IS_SYNC_BOOLEAN = true;
    public static final Integer IS_NOT_ACTIVE = 0;
    public static final Integer OTP_DURATION = 2;
    public static final Integer IS_SELF = 1;
    public static final Integer IMAGE_UPLOAD_MAX_SIZE = 2;
    public static final String IMAGE_UPLOAD_PATH = "/images/";
    public static final String EXCEL_EXPORT_PATH = "/images/";
    public static final String PATTERN_DATE_EXCEL_EXPORT = "yyyyMMdd_HHmm";
    public static final String PATTERN_SHORT_DATE = "dd/MM/yyyy";
    public static final Integer SYS_USER_PATIENT = 0;
    public static final Integer SYS_USER_DOCTOR = 1;
    public static final String DEFAULT_PSW = "vtsTele#!kjk20202";
    public static final String MALE_GENDER = "Nam";
    public static final String FEMALE_GENDER = "Nữ";
    public static String STR_EMTY = "";
    public static String FILE_MESS = "message";
    public static Integer SUCCESS_CODE = 1;
    public static final String VIETNAM_CODE = "vi";
    public static final String ENGLISH_CODE = "en";
    public static boolean IS_ACTIVE_BOOLEAN = true;
    public static boolean IS_NOT_DELETE_BOOLEAN = false;
    public static String HL7_HEART_BEAT_CODE = "8867-4";
    public static String HL7_BLOOD_PRESSURE_MIN_CODE = "8462-4";
    public static String HL7_BLOOD_PRESSURE_MAX_CODE = "8480-6";
    public static String HL7_PULSE_CODE = "8898-9";
    public static String HL7_TEMPERATURE_CODE = "8310-5";
    public static String HL7_BLOOD_SUGAR_CODE = "8968-0";
    public static String HL7_SPO2_SCORE_CODE = "59408-5";
    public static String HL7_BREATH_CODE = "9279-1";
    public static String HL7_WEIGHT_CODE = "29463-7";
    public static String HL7_HEIGHT_CODE = "8302-2";
    public static String HL7_BMI_CODE = "39156-5";
    public static String HL7_KCAL_CODE = "55421-2";
    public static String HL7_PACE_CODE = "55423-8";
    public static String HL7_WAIST_CIRCUMFERENCE_CODE = "11947-9";
    public static Integer RELATIONSHIP_ID_HIMSELF = 1;
    public static Integer RELATIONSHIP_ID_OTHERS = 17;
    public static String SUCCESS_CREATE_COVID_PATIENT = "SUCCESS_CREATE_COVID_PATIENT";
    public static String FAIL_CREATE_COVID_PATIENT = "FAIL_CREATE_COVID_PATIENT";
    public static Boolean SYNC_TELECARE = true;
    public static Boolean NOT_SYNC_TELECARE = false;
    public static String SYNC_TELECARE_RESULT = "Đã đồng bộ Telecare";
    public static String NOT_SYNC_TELECARE_RESULT = "Chưa đồng bộ Telecare";
    public static Integer COVID_CONTENT_TYPE = 7;
    public static String HL7_EXT_URL_ALLERGY_GROUP = "http://dmdc.kcb.vn/extension/DiUng/NhomDiUng";
    public static String HL7_EXT_URL_EXAM_TYPE = "http://dmdc.kcb.vn/dmloaihinhkcb";
    public static String HL7_EXT_URL_RIGHT_EYE_GLASS = "http://dmdc.kcb.vn/extension/matphaicokinh";
    public static String HL7_EXT_URL_RIGHT_EYE_NO_GLASS = "http://dmdc.kcb.vn/extension/matphaikhongkinh";
    public static String HL7_EXT_URL_LEFT_EYE_GLASS = "http://dmdc.kcb.vn/extension/mattraicokinh";
    public static String HL7_EXT_URL_LEFT_EYE_NO_GLASS = "http://dmdc.kcb.vn/extension/mattraikhongkinh";
    public static String HL7_EXT_URL_VACCINATION_RESULT = "http://dmdc.kcb.vn/extension/ketquatonghop";

    public static final long MAX_INTERVAL_TO_EDIT_SYMPTOM_RESULT = 60L * 60 * 24 * 1000;
    public static final Integer SYMPTOM_RESULT_CREATED_BY_PATIENT = 0;
    public static final String FILE_TYPE_XLSX = "xlsx";

    /**
     * Ma loi tra ve cua ki tren di dong
     */
    public static String ERR_USER_PERMISSION = "ERR_USER_PERMISSION";
    public static String ERR_DATA_PATIENT_NOT_EXIST = "ERR_DATA_PATIENT_NOT_EXIST";
    public static final String DOCTOR_IS_NOT_IN_HEALTH_FACILITY = "DOCTOR_IS_NOT_IN_HEALTH_FACILITY";
    public static final String ERROR_SAVE_DATA = "ERROR_SAVE_DATA";
    public static final String ERROR_SYNC_DATA_HSSK = "ERROR_SYNC_DATA_HSSK";
    public static final String SUCCESS_SYNC_DATA_HSSK = "SUCCESS_SYNC_DATA_HSSK";
    public static final String ERROR_PID_NOT_FOUND = "ERROR_PID_NOT_FOUND";
    public static final String ERROR_PATIENT_NOT_EXIST = "ERROR_PATIENT_NOT_EXIST";
    public static final String ERROR_INPUTPARAMS = "ERROR_INPUTPARAMS";
    public static final String ERROR_INPUTDATEPARAMS = "ERROR_INPUTDATEPARAMS";
    public static final String ERROR_SYNC_INTERVAL_OUT_OF_RANGE = "ERROR_SYNC_INTERVAL_OUT_OF_RANGE";
    public static final String ERROR_EXISTED_PHONE_NUMBER = "ERROR_EXISTED_PHONE_NUMBER";
    public static final String INVALID_PHONE_NUMBER = "INVALID_PHONE_NUMBER";
    public static final String F0_USER_NOT_FOUND = "ERR_COVID_PATIENT_F_NOT_FOUND";
    public static final String ERROR_HEALTH_DECLARATION_HISTORY_NOT_FOUND = "ERROR_HEALTH_DECLARATION_HISTORY_NOT_FOUND";
    public static final String ERROR_HEALTH_DECLARATION_NOT_FOUND = "ERROR_HEALTH_DECLARATION_NOT_FOUND";
    public static final String ERROR_MESSAGE_IMPORT_INCORRECT_FULLNAME = "ERROR_MESSAGE_IMPORT_INCORRECT_FULLNAME";

    //    public interface SIGN_CODE {
//        //Thanh cong
//        public static final Integer SUCCESS = 1;
//        //Loi cert het han
//        public static final Integer ERR_CODE_CEREXPIRE = 2;
//        //loi cert khong ton tai
//        public static final Integer ERR_CODE_NOTFOUND = 3;
//
//    }
    public enum StatusCodeApp {
        CREATED(201, "Tạo thành công"),
        SUCCESS(200, "Thành công"),
        INVALID_PARAMS(400, "Tham số sai định dạng"),
        UNAUTHORIZED(401, "Chưa xác thực"),
        FOBBIDEN(403, "Không có quyền truy cập"),
        NOT_FOUND(404, "Không tìm thấy"),
        INTERNAL_ERROR(500, "Lỗi hệ thống");

        private final int code;
        private final String message;

        private StatusCodeApp(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

        public int getCode() {
            return this.code;
        }

        public HttpStatus getHttpStatus() {
            return HttpStatus.valueOf(code);
        }
    }

    public static final String MENU_MEDIA_TYPE = "MENU";
    public static final String MENU_GROUP_MEDIA_TYPE = "MENU_GROUP";

    public static final String ORDER_BY_POPULAR = "popular";
    public static final String ORDER_BY_NEW = "new";
    public static final String ORDER_BY_NEAREST = "nearest";
    public static final String ORDER_BY_TRENDING = "trending";


    public static final int TABLE_STATUS_READY = 0;
    public static final int TABLE_STATUS_IN_USE = 1;

    public static final int BILL_STATUS_PROGRESS = 0;
    public static final int BILL_STATUS_ACCEPTED = 1;
    public static final int BILL_STATUS_DONE = 2;
    public static final int BILL_STATUS_CANCEL = 3;
    public static final int BILL_STATUS_RECONFIRM = 4;

    public static final int ORDER_ITEM_STATUS_PROGRESS =0;
    public static final int ORDER_ITEM_STATUS_ACCEPTED =1;
    public static final int ORDER_ITEM_STATUS_DONE = 2;
    public static final int ORDER_ITEM_STATUS_DELIVERED =3;
    public static final int ORDER_ITEM_STATUS_RECONFIRM =4;
    public static final int ORDER_ITEM_STATUS_CANCEL = 5;

}
