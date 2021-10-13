package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.CatsShopEntity;
import com.viettel.etc.repositories.tables.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepositoryJPA extends JpaRepository<ShopEntity, Integer> {
    @Query(value = "Select * FROM shops WHERE CREATE_USER_ID = :createUserId  AND is_active = 1 AND ( name like %:keySearch% OR address LIKE %:keySearch% OR phone LIKE %:keySearch%') ",nativeQuery = true)
    List<ShopEntity> getAllByCreateUserIdAndIsActive(Integer createUserId, String keySearch);

    List<ShopEntity> getAllByIsActive(Integer isActive);
}