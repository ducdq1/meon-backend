package com.mrlep.meon.services.impl;

import com.mrlep.meon.config.ConfigValue;
import com.mrlep.meon.dto.object.MediaItem;
import com.mrlep.meon.dto.request.CreateMediaRequest;
import com.mrlep.meon.repositories.MediasRepository;
import com.mrlep.meon.repositories.tables.MediaRepositoryJPA;
import com.mrlep.meon.repositories.tables.PaymentInfoRepositoryJPA;
import com.mrlep.meon.repositories.tables.UsersRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.services.MediaService;
import com.mrlep.meon.services.PaymentInfoService;
import com.mrlep.meon.utils.Constants;
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
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Override
    public Object getPaymentInfoByShop(Integer shopId) throws TeleCareException {
        return paymentInfoRepositoryJPA.getAllByShopIdAndIsActive(shopId, Constants.IS_ACTIVE);
    }

    @Autowired
    private PaymentInfoRepositoryJPA paymentInfoRepositoryJPA;

    @Override
    public Object addPaymentInfo(PaymentInfoEntity request) throws TeleCareException {
        if (request.getId() != null) {
            PaymentInfoEntity paymentInfoEntity = paymentInfoRepositoryJPA.getByIdAndIsActive(request.getId(), Constants.IS_ACTIVE);
            if (paymentInfoEntity != null) {
                paymentInfoEntity.setBankName(request.getBankName());
                paymentInfoEntity.setCardName(request.getCardName());
                paymentInfoEntity.setCardNumber(request.getCardNumber());
                paymentInfoEntity.setUpdateUserId(request.getCreateUserId());
                paymentInfoEntity.setUpdateDate(new Date());
                return paymentInfoRepositoryJPA.save(paymentInfoEntity);
            }
            return null;
        } else {
            request.setCreateDate(new Date());
            request.setIsActive(Constants.IS_ACTIVE);
        }

        return paymentInfoRepositoryJPA.save(request);
    }

    @Override
    public Object deletePaymentInfo(Integer paymentInfoId,Integer userId) throws TeleCareException {
        PaymentInfoEntity entity = paymentInfoRepositoryJPA.getByIdAndIsActive(paymentInfoId, Constants.IS_ACTIVE);
        if (entity != null) {
            entity.setUpdateUserId(userId);
            entity.setUpdateDate(new Date());
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            paymentInfoRepositoryJPA.save(entity);

            return true;
        }
        return null;
    }
}