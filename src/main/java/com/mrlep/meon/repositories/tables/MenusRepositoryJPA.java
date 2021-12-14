package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.MenuEntity;
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
    List<MenuEntity> getAllByShopIdAndMenuGroupIdAndIsActiveOrderByOrderPriorityDesc(Integer shopId, Integer menuGroupId, Integer isActive);

    List<MenuEntity> getAllByShopIdAndIsActiveOrderByOrderPriorityDesc(Integer shopId, Integer isActive);

    MenuEntity getByIdAndIsActive(Integer id, Integer isActive);

    @Query(value = "UPDATE MENU m SET m.order_number = m.order_number + 1 WHERE m.id= :menuId ", nativeQuery = true)
    void updateOrderNumber(Integer menuId);
}