package com.viettel.etc.repositories.tables.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{
    @JsonIgnore
    @Column(name = "IS_ACTIVE")
   protected Integer isActive;

    @JsonIgnore
    @Column(name = "CREATE_USER_ID")
    protected Integer createUserId;

    @JsonIgnore
    @Column(name = "CREATE_DATE")
    protected Date createDate;

    @JsonIgnore
    @Column(name = "UPDATE_DATE")
    protected  Date updateDate;

    @JsonIgnore
    @Column(name = "UPDATE_USER_ID")
    protected  Integer updateUserId;

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
}