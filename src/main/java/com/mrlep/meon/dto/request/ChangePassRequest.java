package com.mrlep.meon.dto.request;

public class ChangePassRequest {
    private String newPass;
    private String pass;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
