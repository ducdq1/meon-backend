package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SearchShopResponse {
    Integer id;
    String name;
    String address;
    String phone;
    String imageUrl;
    String distance;
    String shopTypeName;
    String shopTypeIcon;
    String tags;
    String openTime;
    String closeTime;
    Integer likeNumber;
    String specialTag;
    Boolean isVerify;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public String getShopTypeIcon() {
        return shopTypeIcon;
    }

    public void setShopTypeIcon(String shopTypeIcon) {
        this.shopTypeIcon = shopTypeIcon;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getVerify() {
        return isVerify;
    }

    public void setVerify(Boolean verify) {
        isVerify = verify;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getSpecialTag() {
        return specialTag;
    }

    public void setSpecialTag(String specialTag) {
        this.specialTag = specialTag;
    }
}
