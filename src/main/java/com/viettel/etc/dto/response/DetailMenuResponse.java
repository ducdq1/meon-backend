package com.viettel.etc.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.repositories.tables.entities.MediaEntity;
import com.viettel.etc.repositories.tables.entities.MenuEntity;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailMenuResponse {
  private MenuEntity menu;
  private List<MediaEntity> medias;

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public List<MediaEntity> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaEntity> medias) {
        this.medias = medias;
    }
}
