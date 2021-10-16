package com.viettel.etc.repositories;

import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

public interface ShopRepository {
    ResultSelectEntity search(SearchShopsRequest request);
}
