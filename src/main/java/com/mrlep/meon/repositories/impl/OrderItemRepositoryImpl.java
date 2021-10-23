package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.OrderItemRepository;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
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
        sql.append(" m.id menuId ,m.name menuName,m.unit ");
        sql.append("  FROM ORDER_ITEM o JOIN MENU m on m.id = o.menu_id");
        sql.append("  WHERE o.bill_id=:billId AND o.is_active = 1 ORDER BY o.create_date ");
        params.put("billId", billId);

        return (List<OrderItem>) getListData(sql, params, 0, null, OrderItem.class);
    }
}
