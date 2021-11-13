package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface MediasRepository {
    ResultSelectEntity getMediasByShop(Integer shopId,String objectType,String mediaType,Integer startRecord,Integer pageSize);
}
