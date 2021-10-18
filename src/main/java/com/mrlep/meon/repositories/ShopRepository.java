package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

public interface ShopRepository {
    ResultSelectEntity getRecommendShop(SearchShopsRequest request);
}
