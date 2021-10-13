package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MenuEntity;
import com.viettel.etc.repositories.tables.entities.MenuGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Doctors
 *
 * @author ToolGen
 * @date Thu Sep 23 08:19:38 ICT 2021
 */
@Repository
public interface MenuGroupsRepositoryJPA extends JpaRepository<MenuGroupEntity, Integer> {
    List<MenuGroupEntity> getAllByShopIdAndIsActive(Integer shopId,Integer isActive);
}