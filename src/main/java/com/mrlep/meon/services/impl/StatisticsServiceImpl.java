package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.object.*;
import com.mrlep.meon.dto.request.*;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.dto.response.StatisticsBillResponse;
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
        if (response != null && response.getTotalMoney() != null) {
            response.setItems(statisticsRepository.statisticsBill(request));
        }

        return response;
    }
}