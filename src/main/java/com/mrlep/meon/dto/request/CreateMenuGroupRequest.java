package com.mrlep.meon.dto.request;

import com.mrlep.meon.dto.object.MediaItem;

import java.util.List;

public class CreateMenuGroupRequest {
    private String name;
    private Integer createUserId;
    private Integer shopId;
    private String imageUrl;
    private String tags;
    private List<MediaItem> medias;
    private List<MediaItem> deletedMedias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<MediaItem> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaItem> medias) {
        this.medias = medias;
    }

    public List<MediaItem> getDeletedMedias() {
        return deletedMedias;
    }

    public void setDeletedMedias(List<MediaItem> deletedMedias) {
        this.deletedMedias = deletedMedias;
    }
}
