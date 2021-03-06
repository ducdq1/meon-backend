package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.object.*;
import com.mrlep.meon.dto.request.*;
import com.mrlep.meon.dto.response.*;
import com.mrlep.meon.firebase.FirestoreBillManagement;
import com.mrlep.meon.repositories.*;
import com.mrlep.meon.repositories.tables.*;
import com.mrlep.meon.repositories.tables.entities.*;
import com.mrlep.meon.services.*;
import com.mrlep.meon.utils.*;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public StatisticsBillResponse getStatisticsByShop(StatisticsBillRequest request) throws TeleCareException {
        StatisticsBillResponse response = new StatisticsBillResponse();
        if (request.getType() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);

            switch (request.getType()) {
                case Constants.STATICTIS_WEEKLY:
                    request.setToDate(new Date());
                    cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
                    request.setFromDate(cal.getTime());
                    break;

                case Constants.STATICTIS_LAST_WEEK:
                    request.setFromDate(new Date());
                    cal.add(Calendar.DATE, -7);
                    cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
                    request.setFromDate(cal.getTime());

                    cal.add(Calendar.DATE, 6);
                    request.setToDate(cal.getTime());
                    break;


                case Constants.STATICTIS_MONTHLY:
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                    request.setFromDate(cal.getTime());
                    request.setToDate(new Date());
                    break;
                default:
                    request.setFromDate(new Date());
                    request.setToDate(new Date());
                    break;
            }
        } else if (request.getFromDate() == null || request.getToDate() == null) {
            return response;
        }

        response = statisticsRepository.statisticsTotalBill(request);
        //if (response != null && response.getTotalMoney() != null) {
            response.setItems(statisticsRepository.statisticsBill(request));
        //}

        return response;
    }

    @Override
    public StatisticsOrderResponse getStatisticsOrderByShop(StatisticsBillRequest request) throws TeleCareException {
        StatisticsOrderResponse response = new StatisticsOrderResponse();
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        Date lastFromDate = null;
        Date lastToDate = null;

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        request.setFromDate(cal.getTime());

        cal.add(Calendar.DATE, 6);
        request.setToDate(cal.getTime());

        cal.add(Calendar.DATE, -8);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        lastFromDate = cal.getTime();

        cal.add(Calendar.DATE, 6);
        lastToDate = cal.getTime();

        List<StatisticsBillItem> currentItems = statisticsRepository.statisticsOder(request);
        request.setFromDate(lastFromDate);
        request.setToDate(lastToDate);
        List<StatisticsBillItem> lastItems = statisticsRepository.statisticsOder(request);
        response.setCurrentItems(currentItems);
        response.setLastItems(lastItems);
        Long currentValue = 0L;
        if (currentItems != null) {

            for (StatisticsBillItem item : currentItems) {
                currentValue += (item.getValue() != null ? item.getValue() : 0L);
            }
        }

        Long lastValue = 0L;
        if (lastItems != null) {
            for (StatisticsBillItem item : lastItems) {
                lastValue += (item.getValue() != null ? item.getValue() : 0L);
            }
        }

        response.setCurrentTotalValue(currentValue);
        response.setLastTotalValue(lastValue);
        Double percent = (currentValue.doubleValue() * 100 / lastValue.doubleValue());
        Double growPercent = percent - 100.0;
        growPercent = Math.round(growPercent*100)/100D;
        response.setGrowPercent(growPercent);


        return response;
    }

    @Override
    public StatisticsOrderByMonthResponse getStatisticsOrderByMonth(Integer shopId) throws TeleCareException {
        StatisticsOrderByMonthResponse response = statisticsRepository.getStatisticsOrderByMonth(shopId);
        Long currentValue = response.getCurrentTotalValue();
        Long lastTotalValue = response.getLastTotalValue();

        if (currentValue != null && lastTotalValue != null && lastTotalValue > 0) {
            Double percent = (currentValue.doubleValue() * 100 / lastTotalValue.doubleValue());
            Double growPercent = percent - 100.0;

            growPercent = Math.round(growPercent*100)/100D;

            response.setGrowPercent(growPercent);


        }
        return response;
    }
}