package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepositoryJPA extends JpaRepository<ShopEntity, Integer> {
    @Query(value = "Select * FROM shop WHERE CREATE_USER_ID = :createUserId  AND is_active = 1 AND  (name like concat('%',:keySearch,'%') OR address like concat('%',:keySearch,'%') OR phone like concat('%',:keySearch,'%') ) ",nativeQuery = true)
    List<ShopEntity> getAllByCreateUserIdAndIsActive(Integer createUserId, String keySearch);

    List<ShopEntity> getAllByIsActive(Integer isActive);

    @Query(value = "Select * FROM shop WHERE CREATE_USER_ID = :createUserId AND ID =:shopId AND is_active = 1 ",nativeQuery = true)
    ShopEntity getByCreateUserIdAndShopId(Integer createUserId, Integer shopId);
    ShopEntity getByIdAndIsActive(Integer id,Integer isActive);

    @Query(value = " Select s.* FROM shop s JOIN STAFF st ON st.shop_id = s.id AND st.user_id =:userId  WHERE st.is_active = 1 AND s.is_active = 1  limit 1 ",nativeQuery = true)
    List<ShopEntity> getByStaff(Integer userId);

    @Query(value = "Select  s.* FROM shop s WHERE s.id not in (select shop_id FROM STAFF st WHERE st.user_id =:userId)  limit 5 ",nativeQuery = true)
    List<ShopEntity> getNotByStaff(Integer userId);

//    @Query(value = "UPDATE shop m SET m.order_number = m.order_number + 1 WHERE m.id= :shopId ", nativeQuery = true)
//    void updateOrderNumber(Integer shopId);
}