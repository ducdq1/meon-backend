package com.viettel.etc.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.repositories.tables.entities.MediaEntity;
import com.viettel.etc.repositories.tables.entities.MenuEntity;
import com.viettel.etc.repositories.tables.entities.ShopEntity;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailShopResponse {
    private ShopEntity shop;
    private List<MediaEntity> medias;

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public List<MediaEntity> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaEntity> medias) {
        this.medias = medias;
    }
}
