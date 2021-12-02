package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.*;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.request.StatisticsBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.dto.response.StatisticsBillResponse;
import com.mrlep.meon.dto.response.StatisticsOrderByMonthResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.StatisticsRepository;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.DateUtility;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.StringUtils;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@Repository
public class StatisticsRepositoryImpl extends CommonDataBaseRepository implements StatisticsRepository {
    @Override
    public List<StatisticsBillItem> statisticsBill(StatisticsBillRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" with temp as (select a.Date  as create_date ");
        sql.append(" from (select :toDate - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY as Date ");
        sql.append(" from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a ");
        sql.append("cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b");
        sql.append(" cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c ");
        sql.append(" ) a ");
        sql.append(" where a.Date between :fromDate and :toDate order by a.Date ) ");
        sql.append(" select sum(if(b.total_money is null,0,b.total_money)) value, DATE_FORMAT( t.create_date, '%d/%m/%Y') date from temp t  left JOIN  ");
        sql.append(" BILL b  on date(b.create_date) = date(t.create_date)  and  b.shop_id = :shopId ");
        sql.append(" AND b.status =:status ");
        sql.append(" group by DATE_FORMAT( t.create_date, '%d/%m/%Y') ");

        params.put("shopId", request.getShopId());
        params.put("toDate", request.getToDate());
        params.put("fromDate", request.getFromDate());
        params.put("status", Constants.BILL_STATUS_DONE);

        System.out.println("statisticsBill ");
        System.out.println("From date: " + DateUtility.format(request.getFromDate()));
        System.out.println("To date: " + DateUtility.format(request.getToDate()));

        return (List<StatisticsBillItem>) getListData(sql, params, null, null, StatisticsBillItem.class);
    }

    @Override
    public StatisticsBillResponse statisticsTotalBill(StatisticsBillRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select sum(total_money) totalMoney,count(b.id) totalBill ");
        sql.append(" From bill b  where  b.shop_id = :shopId AND b.status = :status AND b.create_date between :fromDate and :toDate ");

        params.put("shopId", request.getShopId());
        params.put("toDate", request.getToDate());
        params.put("fromDate", request.getFromDate());
        params.put("status", Constants.BILL_STATUS_DONE);

        System.out.println("statisticsBill ");
        System.out.println("From date: " + DateUtility.format(request.getFromDate()));
        System.out.println("To date: " + DateUtility.format(request.getToDate()));

        return (StatisticsBillResponse) getFirstData(sql, params, StatisticsBillResponse.class);
    }

    @Override
    public List<StatisticsBillItem> statisticsOder(StatisticsBillRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" with temp as (select a.Date  as create_date ");
        sql.append(" from (select :toDate - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY as Date ");
        sql.append(" from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a ");
        sql.append("cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b");
        sql.append(" cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c ");
        sql.append(" ) a ");
        sql.append(" where a.Date between :fromDate and :toDate order by a.Date ) ");
        sql.append(" select count(b.id ) value , DATE_FORMAT( t.create_date, '%d/%m/%Y') date from temp t  left JOIN  ");
        sql.append(" order_item b  on date(b.create_date) = date(t.create_date)  AND exists (select id from bill bi where bi.shop_id = :shopId and bi.id = b.bill_id)  ");
        sql.append(" AND b.status =:status ");
        sql.append(" group by DATE_FORMAT( t.create_date, '%d/%m/%Y') order by t.create_date");

        params.put("shopId", request.getShopId());
        params.put("toDate", request.getToDate());
        params.put("fromDate", request.getFromDate());
        params.put("status", Constants.ORDER_ITEM_STATUS_DELIVERED);

        System.out.println("statisticsOrder ");
        System.out.println("From date: " + DateUtility.format(request.getFromDate()));
        System.out.println("To date: " + DateUtility.format(request.getToDate()));

        return (List<StatisticsBillItem>) getListData(sql, params, null, null, StatisticsBillItem.class);
    }

    @Override
    public StatisticsOrderByMonthResponse getStatisticsOrderByMonth(Integer shopId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select ");
        sql.append(" ( select count(b.id ) lastTotalValue from order_item b     " +
                "where b.status = :status and exists (select id from bill bi where bi.shop_id = :shopId and bi.id = b.bill_id) " +
                "and b.create_date between :fromDate1 and :toDate1 ) currentTotalValue,");
        sql.append(" ( select count(b.id ) currentTotalValue from  order_item b " +
                "where b.status = :status and exists (select id from bill bi where bi.shop_id = :shopId and bi.id = b.bill_id) " +
                "and b.create_date between :fromDate2 and :toDate2 ) lastTotalValue ");

        params.put("shopId", shopId);
        params.put("status", Constants.ORDER_ITEM_STATUS_DELIVERED);

        Calendar cal = Calendar.getInstance();
        int max = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, 1);
        params.put("fromDate1", cal.getTime());
        cal.set(Calendar.DATE, max);
        params.put("toDate1", cal.getTime());

        cal.add(Calendar.MONTH,-1);
        cal.set(Calendar.DATE, 1);
        params.put("fromDate2", cal.getTime());
        max = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, max);
        params.put("toDate2", cal.getTime());

        return (StatisticsOrderByMonthResponse) getFirstData(sql, params,   StatisticsOrderByMonthResponse.class);
    }


}
