package com.mrlep.meon.services.impl;

import com.mrlep.meon.repositories.tables.HomeBannerRepositoryJPA;
import com.mrlep.meon.services.HomeBannerService;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Autogen class: Lop danh muc chi so sp02
 *
 * @author ToolGen
 * @date Thu Sep 23 08:29:44 ICT 2021
 */
@Service
public class HomeBannersServiceImpl implements HomeBannerService {

    @Autowired
    private HomeBannerRepositoryJPA homeBannerRepositoryJPA;



    @Override
    public Object getHomeBanners() throws TeleCareException {
        return homeBannerRepositoryJPA.findAllByIsActiveOrderByOrderNumber(Constants.IS_ACTIVE);
    }
}