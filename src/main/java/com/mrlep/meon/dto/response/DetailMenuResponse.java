package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import com.mrlep.meon.repositories.tables.entities.MenuEntity;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailMenuResponse {
  private MenuEntity menu;
  private List<MenuOptionEntity> options;
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

    public List<MenuOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<MenuOptionEntity> options) {
        this.options = options;
    }
}
