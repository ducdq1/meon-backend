package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.object.UserItem;

import java.util.List;

public interface UserRepository {
    List<UserItem> getUserItem(Integer id);
}
