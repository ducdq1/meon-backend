package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface StaffRepository {
    List<StaffItem> getStaffsByShop(Integer shopId);
    StaffItem getDetailStaff(Integer staffId);
}
