package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.HomeBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeBannerRepositoryJPA extends JpaRepository<HomeBannerEntity, Integer> {
    List<HomeBannerEntity> findAllByIsActiveOrderByOrderNumber(Integer isActive);
}