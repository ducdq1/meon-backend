package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.repositories.tables.entities.StaffEntity;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LoginResponse {
    String token;
    Object user;
    List<StaffItem> staffs;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public List<StaffItem> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffItem> staffs) {
        this.staffs = staffs;
    }
}
