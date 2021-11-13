package com.mrlep.meon.services;

import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.repositories.tables.entities.TableAreaEntity;
import com.mrlep.meon.utils.TeleCareException;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface TableAreaService {
    Object getTableAreaByShop(Integer shopId)throws TeleCareException;
    Object addTableArea(TableAreaEntity entity) throws TeleCareException;
    Object deleteTableArea(Integer tableAreaId) throws TeleCareException;
}