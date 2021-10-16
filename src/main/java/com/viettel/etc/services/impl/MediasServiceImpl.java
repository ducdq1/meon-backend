package com.viettel.etc.services.impl;

import com.viettel.etc.dto.object.MediaItem;
import com.viettel.etc.dto.request.LoginRequest;
import com.viettel.etc.dto.request.RegisterRequest;
import com.viettel.etc.dto.request.VerifyOTPRequest;
import com.viettel.etc.dto.response.LoginResponse;
import com.viettel.etc.repositories.tables.MediaRepositoryJPA;
import com.viettel.etc.repositories.tables.OTPRepositoryJPA;
import com.viettel.etc.repositories.tables.UsersRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MediaEntity;
import com.viettel.etc.repositories.tables.entities.OTPEntity;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
import com.viettel.etc.services.MediaService;
import com.viettel.etc.services.UsersService;
import com.viettel.etc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Autogen class: Lop danh muc chi so sp02
 *
 * @author ToolGen
 * @date Thu Sep 23 08:29:44 ICT 2021
 */
@Service
public class MediasServiceImpl implements MediaService {

    @Autowired
    private UsersRepositoryJPA usersRepositoryJPA;

    @Autowired
    private MediaRepositoryJPA mediaRepositoryJPA;

    @Override
    public Object saveMedias(List<MediaItem> medias, List<MediaItem> deletedMedias,Integer objectId, String objectType, Integer createUser) throws TeleCareException {
        if (medias != null) {
            for (MediaItem media : medias) {
                MediaEntity entity = null;
                if (media.getId() == null) {
                    entity = new MediaEntity();
                    entity.setCreateUserId(createUser);
                    entity.setCreateDate(new Date());

                } else {
                    Optional<MediaEntity> entityOptional = mediaRepositoryJPA.findById(media.getId());
                    if (entityOptional.isPresent()) {
                        entity = entityOptional.get();
                        entity.setUpdateDate(new Date());
                        entity.setUpdateUserId(createUser);
                    }
                }

                if (entity != null) {
                    entity.setMediaType(media.getType());
                    entity.setObjectType(objectType);
                    entity.setUrl(media.getUrl());
                    entity.setObjectId(objectId);
                    entity.setIsActive(Constants.IS_ACTIVE);
                }

                mediaRepositoryJPA.save(entity);

            }
        }

        if(deletedMedias !=null){
            for (MediaItem media: deletedMedias) {
                if(media.getId() != null){
                    Optional<MediaEntity> entityOptional = mediaRepositoryJPA.findById(media.getId());
                    if (entityOptional.isPresent()) {
                        MediaEntity entity = entityOptional.get();
                        entity.setUpdateDate(new Date());
                        entity.setUpdateUserId(createUser);
                        entity.setIsActive(Constants.IS_NOT_ACTIVE);
                        mediaRepositoryJPA.save(entity);
                    }
                }
            }
        }

        return true;
    }


}