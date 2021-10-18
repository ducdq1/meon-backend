package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.CatsShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatsShopRepositoryJPA extends JpaRepository<CatsShopEntity, Integer> {
    List<CatsShopEntity> getAllByIsActive(Integer isActive);
}