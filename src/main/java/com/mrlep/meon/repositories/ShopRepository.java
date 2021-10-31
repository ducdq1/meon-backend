package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.ShopItem;
import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.dto.response.ShopsOfStaffResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface ShopRepository {
    ResultSelectEntity getRecommendShop(SearchShopsRequest request);
    List<StaffItem> getShopOfsStaff(Integer userId);
}
