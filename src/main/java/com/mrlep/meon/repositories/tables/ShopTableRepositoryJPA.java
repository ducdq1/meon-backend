package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopTableRepositoryJPA extends JpaRepository<ShopTableEntity, Integer> {
    List<ShopTableEntity> getAllByShopIdAndIsActiveOrderByUniqueNumberAsc(Integer shopId, Integer isActive);
    List<ShopTableEntity> getAllByShopIdAndIsActiveAndUniqueNumber(Integer shopId, Integer isActive,Integer uinqueNumber);
    ShopTableEntity getByIdAndIsActive(Integer id, Integer isActive);
}