package com.viettel.etc.utils;


import java.util.function.Supplier;
/**
 *
 * @author datnv5
 */
public class TeleCareException extends Exception implements Supplier<TeleCareException> {
    private ErrorApp errorApp;
    private Integer codeError;

    public TeleCareException(ErrorApp errorApp) {
        super(errorApp.getDescription());
        this.errorApp = errorApp;
    }

    public TeleCareException(String message) {
        super(message);
    }

    public TeleCareException(Integer codeError, String message) {
        super(message);
        this.codeError = codeError;
    }


    public TeleCareException(ErrorApp errorApp, String message,int code) {
        this.errorApp = errorApp;
        this.errorApp.setDescription(message);
        this.errorApp.setCode(code);
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ErrorApp getErrorApp() {
        return errorApp;
    }

    public Integer getCodeError() {
        return codeError;
    }

    @Override
    public TeleCareException get() {
        return this;
    }
}
