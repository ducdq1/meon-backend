package com.mrlep.meon.dto.object;

import java.util.Date;

public class BillMembersItem {
    Integer billId;
    Integer id;
    String userName;
    Integer userId;
    String phone;
    String avatar;
    String joinTime;
    Integer isBlackList;

    public Integer getIsBlackList() {
        return isBlackList;
    }

    public void setIsBlackList(Integer isBlackList) {
        this.isBlackList = isBlackList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }
}
