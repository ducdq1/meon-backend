package com.mrlep.meon.dto.object;

import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;

import java.util.List;

public class OrderItem {
    Integer shopId;
    Integer id;
    Integer menuId;
    String menuName;
    String menuImageUrl;
    String unit;
    String description;
    String billName;
    Integer billId;
    String cancelMessage;
    Integer status;
    Double amount;
    Integer money;
    Integer price;
    String reconfirms;
    Integer priority;
    String menuOptionIds;
    List<MenuOptionEntity> menuOptions;
    String userName;
    String userAvatar;
    String userPhone;
    String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getCancelMessage() {
        return cancelMessage;
    }

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getReconfirms() {
        return reconfirms;
    }

    public void setReconfirms(String reconfirms) {
        this.reconfirms = reconfirms;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMenuOptionIds() {
        return menuOptionIds;
    }

    public void setMenuOptionIds(String menuOptionIds) {
        this.menuOptionIds = menuOptionIds;
    }

    public List<MenuOptionEntity> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(List<MenuOptionEntity> menuOptions) {
        this.menuOptions = menuOptions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getMenuImageUrl() {
        return menuImageUrl;
    }

    public void setMenuImageUrl(String menuImageUrl) {
        this.menuImageUrl = menuImageUrl;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }
}
