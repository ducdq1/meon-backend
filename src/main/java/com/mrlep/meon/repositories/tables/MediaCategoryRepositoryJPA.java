package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.MediaCategoryEntity;
import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaCategoryRepositoryJPA extends JpaRepository<MediaCategoryEntity, Integer> {
    MediaCategoryEntity getByIdAndIsActive(Integer id, Integer isActive);
}