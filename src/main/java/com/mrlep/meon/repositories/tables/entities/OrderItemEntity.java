package com.mrlep.meon.repositories.tables.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Action_log
 *
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItemEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "MENU_ID")
    Integer menuId;

    @Column(name = "description")
    String description;

    @Column(name = "BILL_ID")
    private Integer billId;

    @Column(name = "cancel_message")
    private String cancelMessage;

    @Column(name = "STATUS")
    Integer status;

    @Column(name = "AMOUNT")
    Double amount;

    @JsonIgnore
    @Column(name = "IS_ACTIVE")
    Integer isActive;

    @Column(name = "MONEY")
    Integer money;

    @Column(name = "PRICE")
    Integer price;

    @Column(name = "UNIT")
    private String unit;

    @Column(name = "MENU_NAME")
    private String menuName;

    @JsonIgnore
    @Column(name = "CREATE_USER_ID")
    Integer createUserId;

    @JsonIgnore
    @Column(name = "CREATE_DATE")
    Date createDate;

    @JsonIgnore
    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @JsonIgnore
    @Column(name = "UPDATE_USER_ID")
    Integer updateUserId;

    @Column(name = "reconfirms")
    String reconfirms;

    @Column(name = "priority")
    Integer priority;

    @Column(name = "menu_option_ids")
    String menuOptionIds;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
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

    public String getMenuOptionIds() {
        return menuOptionIds;
    }

    public void setMenuOptionIds(String menuOptionIds) {
        this.menuOptionIds = menuOptionIds;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}