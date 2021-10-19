package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.MenuEntity;
import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
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
public interface BillRepositoryJPA extends JpaRepository<BillEntity, Integer> {
    List<BillEntity> getAllByShopIdAndIsActiveOrderByCreateDateDesc(Integer shopId, Integer isActive);

    @Query(value = "SELECT count(*) FROM BILL WHERE is_active =1 AND shop_id =:shopId AND table_id =:tableId AND status = 0",nativeQuery = true)
    Integer checkExistBillAndTable(Integer shopId,Integer tableId);

    BillEntity findByIdAndIsActive(Integer id,Integer isActive);
}