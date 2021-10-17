package com.viettel.etc.repositories;

import com.viettel.etc.dto.request.SearchMenusRequest;
import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

public interface MenusRepository {
    ResultSelectEntity getRecommendMenus(SearchMenusRequest request);
}
