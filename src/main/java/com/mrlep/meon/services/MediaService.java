package com.mrlep.meon.services;

import com.mrlep.meon.dto.object.MediaItem;
import com.mrlep.meon.utils.TeleCareException;

import java.util.List;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface MediaService {
    Object saveMedias(List<MediaItem> medias, List<MediaItem> deletedMedias, Integer objectId, String objectType, Integer createUser) throws TeleCareException;
}