package com.mrlep.meon.services;

import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.repositories.tables.entities.QuickSelectMessageEntity;
import com.mrlep.meon.utils.TeleCareException;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface QuickSelectMessageService {
    Object getMessageByShop(Integer shopId,String type)throws TeleCareException;
    Object addMessage(QuickSelectMessageEntity entity) throws TeleCareException;
    Object delete(Integer id,Integer userId) throws TeleCareException;
}