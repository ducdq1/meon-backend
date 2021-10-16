package com.viettel.etc.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SearchShopResponse {
    String name;
    String address;
    String phone;
    String imageUrl;
    Integer distance;
    String shopTypeName;
    String shopTypeIcon;
    String tags;
    Time openTime;
    Time closeTime;
    Double rating;
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
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

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public Boolean getVerify() {
        return isVerify;
    }

    public void setVerify(Boolean verify) {
        isVerify = verify;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getSpecialTag() {
        return specialTag;
    }

    public void setSpecialTag(String specialTag) {
        this.specialTag = specialTag;
    }
}
