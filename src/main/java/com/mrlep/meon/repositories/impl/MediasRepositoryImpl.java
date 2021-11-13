package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.MediasRepository;
import com.mrlep.meon.repositories.tables.entities.MediaCategoryEntity;
import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.StringUtils;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class MediasRepositoryImpl extends CommonDataBaseRepository implements MediasRepository {

    @Override
    public ResultSelectEntity getMediasByShop(Integer shopId, String objectType, Integer startRecord, Integer pageSize) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT b.id,b.url, b.object_id objectId,b.object_type objectType,b.media_type mediaType FROM MEDIA_CATEGORY b ");
        sql.append("  WHERE b.object_id=:shopId AND  b.object_type = :objectType AND b.is_active = 1  ORDER BY b.create_date DESC ");
        params.put("shopId", shopId);
        params.put("objectType", objectType);

        return getListDataAndCount(sql, params, startRecord, pageSize, MediaCategoryEntity.class);
    }
}
