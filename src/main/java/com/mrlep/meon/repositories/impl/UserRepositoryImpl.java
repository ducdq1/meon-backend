package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.object.UserItem;
import com.mrlep.meon.repositories.StaffRepository;
import com.mrlep.meon.repositories.UserRepository;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class UserRepositoryImpl extends CommonDataBaseRepository implements UserRepository {


    @Override
    public List<UserItem> getUserItem(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id,full_name fullName");
        sql.append("  FROM USERS WHERE id=:userId ");
          sql.append(" UNION ALL (SELECT id,full_name fullName FROM USERS WHERE id <> :userId LIMIT 10) ");
        params.put("userId", id);

        return (List<UserItem>) getListData(sql, params, null, null, UserItem.class);
    }
}
