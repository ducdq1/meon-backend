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
@Table(name = "TABLE_AREA")
public class TableAreaEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "AREA_NAME")
    String areaName;

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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}