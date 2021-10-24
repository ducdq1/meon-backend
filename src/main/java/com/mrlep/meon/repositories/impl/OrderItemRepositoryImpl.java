package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.repositories.OrderItemRepository;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class OrderItemRepositoryImpl extends CommonDataBaseRepository implements OrderItemRepository {

    @Override
    public List<OrderItem> getOrderItemOfBill(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT o.id, o.bill_id billId, o.cancel_message cancelMessage,o.status, o.amount , o.money,o.price, o.reconfirms, o.priority,");
        sql.append(" o.id menuId ,o.menu_name menuName,o.unit, o.menu_option_ids menuOptionIds, o.description,u.id userId, u.name userName, u.avatar userAvatar, u.phone userPhone");
        sql.append("  FROM ORDER_ITEM o ");
        sql.append("  JOIN USER u on u.id = o.CREATE_USER_ID ");
        sql.append("  WHERE o.bill_id=:billId AND o.is_active = 1 ORDER BY o.create_date ");
        params.put("billId", billId);

        return (List<OrderItem>) getListData(sql, params, 0, null, OrderItem.class);
    }
}
