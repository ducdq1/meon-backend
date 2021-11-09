package com.mrlep.meon.services;

import com.mrlep.meon.dto.object.MediaItem;
import com.mrlep.meon.dto.request.CreateMediaRequest;
import com.mrlep.meon.utils.TeleCareException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface MediaService {
    Object saveMedias(List<MediaItem> medias, List<MediaItem> deletedMedias, Integer objectId, String objectType, Integer createUser) throws TeleCareException;
    Object uploadFile(MultipartFile [] files, Integer createUserId, CreateMediaRequest request) throws TeleCareException;
    Object getMediasByShop(Integer shopId,String objectType,Integer startRecord,Integer pageSize)throws TeleCareException;
    Object deleteMedia( Integer mediaId, Integer userId) throws TeleCareException;
}