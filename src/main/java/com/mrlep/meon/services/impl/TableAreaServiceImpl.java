package com.mrlep.meon.services.impl;

import com.mrlep.meon.repositories.tables.PaymentInfoRepositoryJPA;
import com.mrlep.meon.repositories.tables.TableAreaRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.repositories.tables.entities.TableAreaEntity;
import com.mrlep.meon.services.PaymentInfoService;
import com.mrlep.meon.services.TableAreaService;
import com.mrlep.meon.utils.Constants;
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
public class TableAreaServiceImpl implements TableAreaService {

    @Autowired
    private TableAreaRepositoryJPA tableAreaRepositoryJPA;

    @Override
    public Object getTableAreaByShop(Integer shopId) throws TeleCareException {
        return tableAreaRepositoryJPA.getAllByShopIdAndIsActive(shopId,Constants.IS_ACTIVE);
    }

    @Override
    public Object addTableArea(TableAreaEntity request) throws TeleCareException {
        if (request.getId() != null) {
            TableAreaEntity tableAreaEntity = tableAreaRepositoryJPA.getByIdAndIsActive(request.getId(), Constants.IS_ACTIVE);
            if (tableAreaEntity != null) {
                tableAreaEntity.setAreaName(request.getAreaName());
                return tableAreaRepositoryJPA.save(tableAreaEntity);
            }
            return null;
        } else {
            request.setCreateDate(new Date());
            request.setIsActive(Constants.IS_ACTIVE);
        }

        return tableAreaRepositoryJPA.save(request);
    }

    @Override
    public Object deleteTableArea(Integer tableAreaId) throws TeleCareException {
        TableAreaEntity entity = tableAreaRepositoryJPA.getByIdAndIsActive(tableAreaId, Constants.IS_ACTIVE);
        if (entity != null) {
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            tableAreaRepositoryJPA.save(entity);
            return true;
        }
        return null;
    }
}