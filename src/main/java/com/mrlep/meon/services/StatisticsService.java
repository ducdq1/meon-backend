package com.mrlep.meon.services;

import com.mrlep.meon.dto.object.StatisticsBillItem;
import com.mrlep.meon.dto.request.StatisticsBillRequest;
import com.mrlep.meon.dto.response.StatisticsBillResponse;
import com.mrlep.meon.dto.response.StatisticsOrderByMonthResponse;
import com.mrlep.meon.dto.response.StatisticsOrderResponse;
import com.mrlep.meon.repositories.tables.entities.QuickSelectMessageEntity;
import com.mrlep.meon.utils.TeleCareException;

import java.text.ParseException;
import java.util.List;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface StatisticsService {
    StatisticsBillResponse getStatisticsByShop(StatisticsBillRequest request)throws TeleCareException;
    StatisticsOrderResponse getStatisticsOrderByShop(StatisticsBillRequest request)throws TeleCareException;
    StatisticsOrderByMonthResponse getStatisticsOrderByMonth(Integer shopId) throws TeleCareException, ParseException;
}