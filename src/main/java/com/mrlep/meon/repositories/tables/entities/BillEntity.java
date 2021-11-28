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
@Table(name = "BILL")
public class BillEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "NAME")
    String name;

    @Column(name = "SHOP_ID")
    Integer shopId;

    @Column(name = "reconfirm_message")
    private String reconfirmMessage;

    @Column(name = "cancel_message")
    private String cancelMessage;

    @Column(name = "STATUS")
    Integer status;

    @JsonIgnore
    @Column(name = "IS_ACTIVE")
    Integer isActive;

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

    @Column(name = "description")
    String description;

    @Column(name = "total_money")
    Integer totalMoney;

    @Column(name = "sub_money_description")
    String subMoneyDescription;

    @Column(name = "sub_money")
    Integer subMoney;

    @Column(name = "VAT")
    Double vat;

    @Column(name = "vat_money")
    Integer vatMoney;

    @Column(name = "is_create_by_staff")
    Integer isCreateByStaff;

    @Column(name = "discount_money")
    Integer discountMoney;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getReconfirmMessage() {
        return reconfirmMessage;
    }

    public void setReconfirmMessage(String reconfirmMessage) {
        this.reconfirmMessage = reconfirmMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getIsCreateByStaff() {
        return isCreateByStaff;
    }

    public void setIsCreateByStaff(Integer isCreateByStaff) {
        this.isCreateByStaff = isCreateByStaff;
    }

    public String getCancelMessage() {
        return cancelMessage;
    }

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    public String getSubMoneyDescription() {
        return subMoneyDescription;
    }

    public void setSubMoneyDescription(String subMoneyDescription) {
        this.subMoneyDescription = subMoneyDescription;
    }

    public Integer getSubMoney() {
        return subMoney;
    }

    public void setSubMoney(Integer subMoney) {
        this.subMoney = subMoney;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Integer getVatMoney() {
        return vatMoney;
    }

    public void setVatMoney(Integer vatMoney) {
        this.vatMoney = vatMoney;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }
}