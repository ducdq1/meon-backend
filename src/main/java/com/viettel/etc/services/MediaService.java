package com.viettel.etc.services;

import com.viettel.etc.dto.object.MediaItem;
import com.viettel.etc.dto.request.LoginRequest;
import com.viettel.etc.dto.request.RegisterRequest;
import com.viettel.etc.dto.request.VerifyOTPRequest;
import com.viettel.etc.utils.TeleCareException;

import java.util.List;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface MediaService {
    Object saveMedias(List<MediaItem> medias,List<MediaItem> deletedMedias,Integer objectId,String objectType,Integer createUser) throws TeleCareException;
}