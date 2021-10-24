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
@Table(name = "BILL_TABLES")
public class BillTablesEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "BILL_ID")
    Integer billId;

    @Column(name = "TABLE_ID")
    Integer tableId;

    @Column(name = "TABLE_NAME")
    String tableName;

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

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


}