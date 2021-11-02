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
@Table(name = "MEDIA")
public class MediaEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "OBJECT_ID")
    Integer objectId;

    @Column(name = "OBJECT_TYPE")
    String objectType;

    @Column(name = "URL")
    String url;

    @Column(name = "MEDIA_TYPE")
    String mediaType;

    @Column(name = "IS_CATEGORY")
    Integer isCategory;

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

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getUrl() {
        return Constants.DOMAIN + url;
    }

    public void setUrl(String url) {
        this.url =  url;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getIsCategory() {
        return isCategory;
    }

    public void setIsCategory(Integer isCategory) {
        this.isCategory = isCategory;
    }
}