package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.BillTableRepository;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.StringUtils;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class BillTableRepositoryImpl extends CommonDataBaseRepository implements BillTableRepository {

    @Override
    public List<String> getBillTableName(Integer billId) {
        return null;
    }
}
