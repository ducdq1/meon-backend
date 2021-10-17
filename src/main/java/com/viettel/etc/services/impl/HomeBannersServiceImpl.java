package com.viettel.etc.services.impl;

import com.viettel.etc.dto.object.MediaItem;
import com.viettel.etc.repositories.tables.HomeBannerRepositoryJPA;
import com.viettel.etc.repositories.tables.MediaRepositoryJPA;
import com.viettel.etc.repositories.tables.UsersRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MediaEntity;
import com.viettel.etc.services.HomeBanenrService;
import com.viettel.etc.services.MediaService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class HomeBannersServiceImpl implements HomeBanenrService {

    @Autowired
    private HomeBannerRepositoryJPA homeBannerRepositoryJPA;



    @Override
    public Object getHomeBanners() throws TeleCareException {
        return homeBannerRepositoryJPA.findAllByIsActiveOrderByOrderNumber(Constants.IS_ACTIVE);
    }
}