package com.mrlep.meon.dto.request;

import com.mrlep.meon.dto.object.MediaItem;

import java.util.List;

public class CreateMenuRequest {
    private String name;
    private Integer menuGroupId;
    private Integer createUserId;
    private Integer shopId;
    private String imageUrl;
    private String description;
    private String discount;
    private String tags;
    private Integer price;
    private  Integer orderPriority;
    private List<MediaItem> medias;
    private List<MediaItem> deletedMedias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(Integer menuGroupId) {
        this.menuGroupId = menuGroupId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(Integer orderPriority) {
        this.orderPriority = orderPriority;
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
