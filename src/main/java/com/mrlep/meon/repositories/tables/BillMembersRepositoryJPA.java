package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.BillMembersEntity;
import com.mrlep.meon.repositories.tables.entities.BillTablesEntity;
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
public interface BillMembersRepositoryJPA extends JpaRepository<BillMembersEntity, Integer> {
    BillMembersEntity findByUserIdAndBillIdAndIsActive(Integer userId,Integer billId, Integer isActive);
    BillMembersEntity findByIdAndIsActive(Integer id, Integer isActive);
    List<BillMembersEntity> findAllByBillIdAndIsActive(Integer billId, Integer isActive);
    List<BillMembersEntity> findAllByBillIdAndUserIdAndIsActive(Integer billId, Integer userId, Integer isActive);

    @Query(value = "SELECT count(*) FROM BILL_MEMBERS o WHERE o.is_active =1 AND o.bill_id = :billId ", nativeQuery = true)
    Integer countMembersOfBill(Integer billId);

}