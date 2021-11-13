package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.repositories.tables.entities.TableAreaEntity;
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
public interface TableAreaRepositoryJPA extends JpaRepository<TableAreaEntity, Integer> {
    List<TableAreaEntity> getAllByShopIdAndIsActive(Integer shopId, Integer isActive);
    TableAreaEntity getByIdAndIsActive(Integer id, Integer isActive);
}