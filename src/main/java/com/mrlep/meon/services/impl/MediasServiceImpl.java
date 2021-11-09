package com.mrlep.meon.services.impl;

import com.mrlep.meon.config.ConfigValue;
import com.mrlep.meon.dto.object.MediaItem;
import com.mrlep.meon.dto.request.CreateMediaRequest;
import com.mrlep.meon.repositories.MediasRepository;
import com.mrlep.meon.repositories.tables.MediaRepositoryJPA;
import com.mrlep.meon.repositories.tables.UsersRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import com.mrlep.meon.services.MediaService;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.DateUtility;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.TeleCareException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private ConfigValue configValue;

    @Autowired
    private MediaRepositoryJPA mediaRepositoryJPA;

    @Autowired
    private MediasRepository mediaRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object saveMedias(List<MediaItem> medias, List<MediaItem> deletedMedias, Integer objectId, String objectType, Integer createUser) throws TeleCareException {
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

        if (deletedMedias != null) {
            for (MediaItem media : deletedMedias) {
                if (media.getId() != null) {
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

    @Override
    @Transactional
    public Object uploadFile(MultipartFile[] files, Integer createUserId, CreateMediaRequest request) throws TeleCareException {
        String folderFile = configValue.getFolderFiles();
        String folderPathFile = configValue.getFolderPathFiles();
        String path = folderFile + File.separator + (new Date().getYear() + 1900) + File.separator + (new Date().getMonth() + 1) + File.separator;
        folderPathFile += File.separator + path;
        List<MediaEntity> medias = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = FnCommon.uploadFile(folderPathFile, file, FilenameUtils.getExtension(file.getOriginalFilename()));

            if (fileName != null) {
                MediaEntity mediaItem = new MediaEntity();
                mediaItem.setUrl(path + fileName);
                mediaItem.setObjectId(request.getShopId());
                mediaItem.setObjectType(request.getObjectType());
                mediaItem.setMediaType(request.getMediaType());
                mediaItem.setIsCategory(1);
                mediaItem.setIsActive(Constants.IS_ACTIVE);
                mediaItem.setCreateDate(new Date());
                mediaItem.setCreateUserId(createUserId);
                medias.add(mediaItem);

            } else {
                rollBackFiles(medias);
                return null;
            }

        }
        saveMedias(medias);
        return medias;
    }

    @Override
    public Object getMediasByShop(Integer shopId, String objectType, Integer startRecord, Integer pageSize) {
        return mediaRepository.getMediasByShop(shopId, objectType, startRecord, pageSize);
    }

    @Override
    public Object deleteMedia(Integer mediaId, Integer userId) throws TeleCareException {
        MediaEntity mediaEntity = mediaRepositoryJPA.getByIdAndIsActive(mediaId, Constants.IS_ACTIVE);
        if (mediaEntity != null) {
            mediaEntity.setIsActive(Constants.IS_NOT_ACTIVE);
            mediaEntity.setUpdateDate(new Date());
            mediaEntity.setUpdateUserId(userId);
            mediaRepositoryJPA.save(mediaEntity);
            if (mediaEntity.getUrl() != null) {
                String filePath = configValue.getFolderPathFiles() + mediaEntity.getUrl();
                FnCommon.deleteFile(filePath);
            }

            return true;
        }
        return null;

    }


    private void rollBackFiles(List<MediaEntity> medias) {
        for (MediaEntity mediaEntity : medias) {
            File f = new File(mediaEntity.getUrl());
            if (f.exists()) {
                f.delete();
            }
        }
    }

    private void saveMedias(List<MediaEntity> medias) {
        mediaRepositoryJPA.saveAll(medias);
    }
}