package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.CatsShopEntity;
import com.viettel.etc.repositories.tables.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepositoryJPA extends JpaRepository<ShopEntity, Integer> {
    List<ShopEntity> getAllByCreateUserIdAndIsActive(Integer createUserId, Integer isActive);

    List<ShopEntity> getAllByIsActive(Integer isActive);
}