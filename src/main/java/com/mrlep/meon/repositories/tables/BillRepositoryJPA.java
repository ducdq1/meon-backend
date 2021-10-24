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

    @Query(value = "SELECT count(*) FROM BILL b JOIN BILL_TABLES bt ON b.id = bt.bill_id WHERE b.is_active =1 AND b.shop_id =:shopId AND bt.table_id =:tableId AND b.status = 0",nativeQuery = true)
    Integer checkExistBillAndTable(Integer shopId,Integer tableId);
    BillEntity findByIdAndIsActive(Integer id,Integer isActive);

    @Query(value = "SELECT b FROM BILL b JOIN BILL_TABLES bt  ON bt.bill_id = b.id JOIN SHOP_TABLE t ON t.id = bt.table_id " +
            " WHERE t.id =:tableId AND b.status <> 3 AND b.status <> 4 AND t.is_active = 1",nativeQuery = true)
    List<BillEntity> findByTableIdAndIsActive(Integer tableId);
}