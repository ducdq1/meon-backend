package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface ShopTableRepository {
    List<BillTablesItem> getTableOfBill(Integer billId);
    ResultSelectEntity getTableOfShopAndStatus(Integer shopId,Integer status,Integer startRecord,Integer pageSize);
}
