package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
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
public interface PaymentInfoRepositoryJPA extends JpaRepository<PaymentInfoEntity, Integer> {
    List<PaymentInfoEntity> getAllByShopIdAndIsActive(Integer shopId,Integer isActive);
    PaymentInfoEntity getByIdAndIsActive(Integer id,Integer isActive);
}