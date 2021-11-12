package com.mrlep.meon.services;

import com.mrlep.meon.dto.object.MediaItem;
import com.mrlep.meon.dto.request.CreateMediaRequest;
import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.utils.TeleCareException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface PaymentInfoService {
    Object getPaymentInfoByShop(Integer shopId)throws TeleCareException;
    Object addPaymentInfo(PaymentInfoEntity entity) throws TeleCareException;
    Object deletePaymentInfo(Integer paymentInfoId) throws TeleCareException;
}