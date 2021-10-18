package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.request.SearchMenusRequest;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

public interface MenusRepository {
    ResultSelectEntity getRecommendMenus(SearchMenusRequest request);
}
