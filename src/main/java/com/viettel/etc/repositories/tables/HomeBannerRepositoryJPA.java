package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.HomeBannerEntity;
import com.viettel.etc.repositories.tables.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeBannerRepositoryJPA extends JpaRepository<HomeBannerEntity, Integer> {
    List<HomeBannerEntity> findAllByIsActiveOrderByOrderNumber(Integer isActive);
}