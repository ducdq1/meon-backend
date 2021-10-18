package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepositoryJPA extends JpaRepository<MediaEntity, Integer> {
    List<MediaEntity> findAllByIsActiveAndObjectTypeAndObjectId(Integer isActive,String objectType,Integer objectId);
}