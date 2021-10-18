package com.viettel.etc.dto.request;

import com.viettel.etc.dto.object.MediaItem;

import java.util.List;

public class CreateShopTableRequest {
    private Integer tableId;
    private String name;
    private Integer shopId;
    private Integer uniqueNumber;
    private Integer capability;
    private Integer status = 0;
    private String imageUrl;
    private Integer createUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShopId() {
        return shopId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCapability() {
        return capability;
    }

    public void setCapability(Integer capability) {
        this.capability = capability;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(Integer uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
}
