package com.mrlep.meon.services.impl;

import com.mrlep.meon.repositories.tables.PaymentInfoRepositoryJPA;
import com.mrlep.meon.repositories.tables.QuickSelectMessageRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.repositories.tables.entities.QuickSelectMessageEntity;
import com.mrlep.meon.services.PaymentInfoService;
import com.mrlep.meon.services.QuickSelectMessageService;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.StringUtils;
import com.mrlep.meon.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Autogen class: Lop danh muc chi so sp02
 *
 * @author ToolGen
 * @date Thu Sep 23 08:29:44 ICT 2021
 */
@Service
public class QuickSelectMessageServiceImpl implements QuickSelectMessageService {

    @Autowired
    private QuickSelectMessageRepositoryJPA quickSelectMessageRepositoryJPA;

    @Override
    public Object getMessageByShop(Integer shopId, String type) throws TeleCareException {
        if (!StringUtils.isNullOrEmpty(type)) {
            return quickSelectMessageRepositoryJPA.getAllByShopIdAndTypeAndIsActive(shopId, type, Constants.IS_ACTIVE);
        } else {
            return quickSelectMessageRepositoryJPA.getAllByShopIdAndIsActive(shopId, Constants.IS_ACTIVE);
        }
    }

    @Override
    public Object addMessage(QuickSelectMessageEntity request) throws TeleCareException {
        if (request.getId() != null) {
            QuickSelectMessageEntity quickSelectMessageEntity = quickSelectMessageRepositoryJPA.getByIdAndIsActive(request.getId(), Constants.IS_ACTIVE);
            if (quickSelectMessageEntity != null) {
                quickSelectMessageEntity.setMessage(request.getMessage());
                quickSelectMessageEntity.setType(request.getType());
                quickSelectMessageEntity.setUpdateDate(new Date());
                quickSelectMessageEntity.setUpdateUserId(request.getCreateUserId());
                return quickSelectMessageRepositoryJPA.save(quickSelectMessageEntity);
            }
            return null;
        } else {
            request.setCreateDate(new Date());
            request.setIsActive(Constants.IS_ACTIVE);
        }

        return quickSelectMessageRepositoryJPA.save(request);
    }

    @Override
    public Object delete(Integer id, Integer userId) throws TeleCareException {
        QuickSelectMessageEntity entity = quickSelectMessageRepositoryJPA.getByIdAndIsActive(id, Constants.IS_ACTIVE);
        if (entity != null) {
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            entity.setUpdateUserId(userId);
            entity.setUpdateDate(new Date());
            quickSelectMessageRepositoryJPA.save(entity);
            return true;
        }

        return null;
    }
}