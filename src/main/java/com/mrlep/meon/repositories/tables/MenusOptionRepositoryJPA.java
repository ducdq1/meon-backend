package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.MenuEntity;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;
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
public interface MenusOptionRepositoryJPA extends JpaRepository<MenuOptionEntity, Integer> {
    MenuOptionEntity getByIdAndIsActive(Integer id, Integer isActive);
    List<MenuOptionEntity> getAllByMenuIdAndIsActive(Integer menuId, Integer isActive);
}