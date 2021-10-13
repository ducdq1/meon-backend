package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MenuEntity;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Doctors
 *
 * @author ToolGen
 * @date Thu Sep 23 08:19:38 ICT 2021
 */
@Repository
public interface MenusRepositoryJPA extends JpaRepository<MenuEntity, Integer> {
    List<MenuEntity> getAllByShopIdAndMenuGroupIdAndIsActive(Integer shopId,Integer menuGroupId,Integer isActive);
}