package com.mrlep.meon.repositories.tables.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrlep.meon.utils.Constants;
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
@Table(name = "PAYMENT_INFO")
public class PaymentInfoEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "CARD_NAME")
    String cardName;

    @Column(name = "CARD_NUMBER")
    String cardNumber;

    @Column(name = "BANK_NAME")
    String bankName;

    @Column(name = "SHOP_ID")
    Integer shopId;

    @JsonIgnore
    @Column(name = "IS_ACTIVE")
     Integer isActive;

    @JsonIgnore
    @Column(name = "CREATE_USER_ID")
     Integer createUserId;

    @JsonIgnore
    @Column(name = "CREATE_DATE")
     Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

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
}