package com.mrlep.meon.dto.request;

import javax.persistence.Column;
import java.util.List;

public class CreateStaffRequest {
    Integer staffId;
    Integer shopId;
    Integer userId;
    Integer createUserId;
    String permission;
    String identityNumber;
    String certification;
    Integer salary;
    Integer status;

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
