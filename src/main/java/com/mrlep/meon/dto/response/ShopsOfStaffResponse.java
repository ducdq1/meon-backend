package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.ShopItem;
import lombok.Data;

import java.util.List;

public class ShopsOfStaffResponse {
    Integer staffId;
    String roles;
    List<ShopItem> shops;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<ShopItem> getShops() {
        return shops;
    }

    public void setShops(List<ShopItem> shops) {
        this.shops = shops;
    }
}
