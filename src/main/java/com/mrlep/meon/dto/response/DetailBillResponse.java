package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrlep.meon.dto.object.BillMembersObject;
import com.mrlep.meon.dto.object.BillTablesObject;
import com.mrlep.meon.repositories.tables.entities.BillMembersEntity;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailBillResponse {
    Integer billId;
    Integer shopId;
    String billName;
    Integer billStatus;
    String description;
    Integer totalMoney;
    String userName;
    String phone;
    String avatar;
    List<OrderItemEntity> orderItems;
    List<BillTablesObject> tables;
    List<BillMembersObject> members;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public List<BillTablesObject> getTables() {
        return tables;
    }

    public void setTables(List<BillTablesObject> tables) {
        this.tables = tables;
    }

    public List<BillMembersObject> getMembers() {
        return members;
    }

    public void setMembers(List<BillMembersObject> members) {
        this.members = members;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
