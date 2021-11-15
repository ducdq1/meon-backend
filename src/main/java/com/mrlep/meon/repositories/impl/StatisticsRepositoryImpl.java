package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.StatisticsBillItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.request.StatisticsBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.StatisticsRepository;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.StringUtils;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

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
        sql.append(" from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a " );
        sql.append(  "cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b");
        sql.append(" cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c ");
        sql.append(" ) a ");
        sql.append(" where a.Date between :fromDate and :toDate order by a.Date ) ");
        sql.append(" select  sum(if(b.total_money is null,0,b.total_money)) totalMoney, DATE_FORMAT( t.create_date, '%d/%m/%Y') createDate from temp t  left JOIN  ");
        sql.append("bill b  on date(b.create_date) = date(t.create_date)  and  b.shop_id = :shopId ");

        sql.append(" group by DATE_FORMAT( t.create_date, '%d/%m/%Y') ");

        params.put("shopId", request.getShopId());
        params.put(":toDate", request.getToDate());
        params.put(":fromDate", request.getFromDate());

        return (List<StatisticsBillItem>) getListData(sql, params, null,null,StatisticsBillItem.class);
    }
}
