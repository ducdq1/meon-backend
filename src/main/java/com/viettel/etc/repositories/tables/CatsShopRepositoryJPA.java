package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.CatsShopEntity;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatsShopRepositoryJPA extends JpaRepository<CatsShopEntity, Integer> {
    List<CatsShopEntity> getAllByIsActive(Integer isActive);
}