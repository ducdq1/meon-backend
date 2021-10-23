package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.StaffEntity;
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
public interface StaffRepositoryJPA extends JpaRepository<StaffEntity, Integer> {
    List<StaffEntity> getAllByShopIdAndIsActiveOrderByCreateDateDesc(Integer shopId, Integer isActive);
    StaffEntity getByIdAndIsActive(Integer id, Integer isActive);
    StaffEntity getByUserIdAndShopIdAndIsActive(Integer userId,Integer shopId, Integer isActive);

    List<StaffEntity> getAllByUserIdAndIsActive(Integer id, Integer isActive);
}