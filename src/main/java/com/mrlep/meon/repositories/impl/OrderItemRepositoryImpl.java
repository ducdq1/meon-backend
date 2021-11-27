package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.repositories.OrderItemRepository;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class OrderItemRepositoryImpl extends CommonDataBaseRepository implements OrderItemRepository {

    @Override
    public OrderItem getOrderItem(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT o.id, o.bill_id billId,b.name billName,  DATE_FORMAT(o.create_date, '%d-%m-%Y %H:%i') createDate,o.cancel_message cancelMessage,o.status, o.amount , o.money,o.price, o.reconfirms, o.priority,");
        sql.append(" m.id menuId ,o.menu_name menuName,m.IMAGE_URL menuImageUrl, o.unit, o.menu_option_ids menuOptionIds, o.description,u.id userId, u.full_name userName, u.avatar userAvatar, u.phone userPhone, s.id shopId, ");
        sql.append("  o.discount_value discountValue,o.discount_type discountType,o.discount_description discountDescription,m.process_type menuProcessType,b.status billStatus ");
        sql.append("  FROM ORDER_ITEM o ");
        sql.append(" JOIN BILL b ON b.id = o.bill_id ");
        sql.append(" JOIN Shop s ON s.id = b.shop_id ");
        sql.append(" LEFT JOIN MENU m on m.id = o.MENU_ID ");
        sql.append(" JOIN USERS u on u.id = o.CREATE_USER_ID ");
        sql.append(" WHERE o.id=:id AND o.is_active = 1 ORDER BY o.create_date ");
        params.put("id", id);

        return (OrderItem) getFirstData(sql, params, OrderItem.class);
    }

    @Override
    public List<OrderItem> getOrderItemOfBill(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT o.id, o.bill_id billId,b.name billName, DATE_FORMAT(o.create_date, '%d-%m-%Y %H:%i') createDate ,o.cancel_message cancelMessage,o.status, o.amount , o.money,o.price, o.reconfirms, o.priority,");
        sql.append(" m.id menuId ,o.menu_name menuName,m.IMAGE_URL menuImageUrl, o.unit, o.menu_option_ids menuOptionIds, o.description,u.id userId, u.full_name userName, u.avatar userAvatar, u.phone userPhone, s.id shopId,");
        sql.append("  o.discount_value discountValue,o.discount_type discountType,o.discount_description discountDescription,m.process_type menuProcessType ");
        sql.append("  FROM ORDER_ITEM o ");
        sql.append(" JOIN BILL b ON b.id = o.bill_id ");
        sql.append(" JOIN Shop s ON s.id = b.shop_id ");
        sql.append("  LEFT JOIN MENU m on m.id = o.MENU_ID ");
        sql.append("  JOIN USERS u on u.id = o.CREATE_USER_ID ");
        sql.append("  WHERE o.bill_id=:billId AND o.is_active = 1 ORDER BY o.create_date ");
        params.put("billId", billId);

        return (List<OrderItem>) getListData(sql, params, 0, null, OrderItem.class);
    }

    @Override
    public List<OrderItem> getOrderItemDeliveredOfBill(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT o.id, o.bill_id billId,b.name billName, DATE_FORMAT(o.create_date, '%d-%m-%Y %H:%i') createDate ,o.cancel_message cancelMessage,o.status, o.amount , o.money,o.price, o.reconfirms, o.priority,");
        sql.append(" m.id menuId ,o.menu_name menuName,m.IMAGE_URL menuImageUrl, o.unit, o.menu_option_ids menuOptionIds, o.description,u.id userId, u.full_name userName, u.avatar userAvatar, u.phone userPhone, s.id shopId,");
        sql.append("  o.discount_value discountValue,o.discount_type discountType,o.discount_description discountDescription,m.process_type menuProcessType ");
        sql.append("  FROM ORDER_ITEM o ");
        sql.append(" JOIN BILL b ON b.id = o.bill_id ");
        sql.append(" JOIN Shop s ON s.id = b.shop_id ");
        sql.append("  LEFT JOIN MENU m on m.id = o.MENU_ID ");
        sql.append("  JOIN USERS u on u.id = o.CREATE_USER_ID ");
        sql.append("  WHERE o.bill_id=:billId AND o.is_active = 1 AND o.status = :status ORDER BY o.create_date ");
        params.put("billId", billId);
        params.put("status", Constants.ORDER_ITEM_STATUS_DELIVERED);

        return (List<OrderItem>) getListData(sql, params, 0, null, OrderItem.class);
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT o.id,s.id shopId, o.bill_id billId, o.cancel_message cancelMessage,o.status, o.amount , o.money,o.price, o.reconfirms, o.priority,");
        sql.append(" m.id menuId ,o.menu_name menuName,m.IMAGE_URL menuImageUrl, o.unit, o.menu_option_ids menuOptionIds, o.description,u.id userId, u.full_name userName, u.avatar userAvatar, u.phone userPhone,");
        sql.append("  o.discount_value discountValue,o.discount_type discountType,o.discount_description discountDescription,m.process_type menuProcessType ");
        sql.append("  FROM ORDER_ITEM o ");
        sql.append("  JOIN BILL b on b.id = o.bill_id ");
        sql.append("  JOIN SHOP s on s.id = b.shop_id ");
        sql.append("  LEFT JOIN MENU m on m.id = o.MENU_ID ");
        sql.append("  JOIN USERS u on u.id = o.CREATE_USER_ID ");
        sql.append("  WHERE o.is_active = 1  ");
        sql.append("  AND b.is_active = 1  AND b.status <> :statusDone AND b.status <> :statusCancel");
        sql.append("    ORDER BY o.create_date ");

        params.put("statusCancel", Constants.BILL_STATUS_CANCEL);
        params.put("statusDone", Constants.BILL_STATUS_DONE);

        return (List<OrderItem>) getListData(sql, params, 0, null, OrderItem.class);
    }
}
