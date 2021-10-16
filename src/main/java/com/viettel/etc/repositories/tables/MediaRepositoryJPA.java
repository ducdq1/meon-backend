package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MediaEntity;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepositoryJPA extends JpaRepository<MediaEntity, Integer> {
    List<MediaEntity> findAllByIsActiveAndObjectTypeAndObjectId(Integer isActive,String objectType,Integer objectId);
}