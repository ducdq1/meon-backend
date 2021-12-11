package com.mrlep.meon.dto.object;

import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;

import java.util.List;

public class CountOrderItem {
    Integer menuId;
    String menuName;
    String menuImageUrl;
    Integer price;
    String tags;
    Integer orderNumber;

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

    public String getMenuImageUrl() {
        return menuImageUrl;
    }

    public void setMenuImageUrl(String menuImageUrl) {
        this.menuImageUrl = menuImageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
