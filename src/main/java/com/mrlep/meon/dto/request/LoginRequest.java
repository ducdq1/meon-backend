package com.mrlep.meon.dto.request;

public class LoginRequest {
    private String phone;
    private String pass;
    private boolean isShopMode;
    private Integer shopId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isShopMode() {
        return isShopMode;
    }

    public void setShopMode(boolean shopMode) {
        isShopMode = shopMode;
    }


    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
