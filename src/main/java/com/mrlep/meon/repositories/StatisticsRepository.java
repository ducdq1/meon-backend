package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.StatisticsBillItem;
import com.mrlep.meon.dto.object.StatisticsOrderItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.request.StatisticsBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.dto.response.StatisticsBillResponse;
import com.mrlep.meon.dto.response.StatisticsOrderByMonthResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface StatisticsRepository {
    List<StatisticsBillItem> statisticsBill(StatisticsBillRequest request);
    StatisticsBillResponse statisticsTotalBill(StatisticsBillRequest request);
    List<StatisticsBillItem> statisticsOder(StatisticsBillRequest request);
    StatisticsOrderByMonthResponse getStatisticsOrderByMonth(Integer shopId);
}
