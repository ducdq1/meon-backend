package com.mrlep.meon.dto.request;

import com.mrlep.meon.dto.object.MediaItem;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;

import java.util.List;

public class CreateMenuRequest {
    private String name;
    private Integer menuGroupId;
    private Integer createUserId;
    private Integer shopId;
    private String imageUrl;
    private String description;
    private String discountDescription;
    private Double discountValue;
    private Integer discountType;
    private String tags;
    private Integer price;
    private Integer orderPriority;
    private String unit;
    private Integer isOddNumber;
    private Integer processType;
    private List<MenuOptionEntity> options;
    private List<Integer> deletedOptions;

    private List<Integer> medias;
    private List<Integer> deletedMedias;

    public Integer getProcessType() {
        return processType;
    }

    public void setProcessType(Integer processType) {
        this.processType = processType;
    }

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

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
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

    public List<Integer> getMedias() {
        return medias;
    }

    public void setMedias(List<Integer> medias) {
        this.medias = medias;
    }

    public List<Integer> getDeletedMedias() {
        return deletedMedias;
    }

    public void setDeletedMedias(List<Integer> deletedMedias) {
        this.deletedMedias = deletedMedias;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<MenuOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<MenuOptionEntity> options) {
        this.options = options;
    }

    public List<Integer> getDeletedOptions() {
        return deletedOptions;
    }

    public void setDeletedOptions(List<Integer> deletedOptions) {
        this.deletedOptions = deletedOptions;
    }

    public Integer getIsOddNumber() {
        return isOddNumber;
    }

    public void setIsOddNumber(Integer isOddNumber) {
        this.isOddNumber = isOddNumber;
    }
}
