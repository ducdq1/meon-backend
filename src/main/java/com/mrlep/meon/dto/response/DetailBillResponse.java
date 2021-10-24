package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailBillResponse {
    Integer billId;
    Integer shopId;
    String shopAvatar;
    String shopName;
    String billName;
    Integer billStatus;
    String description;
    Integer totalMoney;
    String userName;
    String phone;
    String avatar;
    List<OrderItem> orderItems;
    List<BillTablesItem> tables;
    List<BillMembersItem> members;

    public String getShopAvatar() {
        return shopAvatar;
    }

    public void setShopAvatar(String shopAvatar) {
        this.shopAvatar = shopAvatar;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<BillTablesItem> getTables() {
        return tables;
    }

    public void setTables(List<BillTablesItem> tables) {
        this.tables = tables;
    }

    public List<BillMembersItem> getMembers() {
        return members;
    }

    public void setMembers(List<BillMembersItem> members) {
        this.members = members;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
