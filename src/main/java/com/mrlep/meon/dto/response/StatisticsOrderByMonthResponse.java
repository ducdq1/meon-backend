package com.mrlep.meon.dto.response;

import com.mrlep.meon.dto.object.StatisticsBillItem;

import java.util.List;

public class StatisticsOrderByMonthResponse {
    Long currentTotalValue;
    Long lastTotalValue;
    Double growPercent;

    public Long getCurrentTotalValue() {
        return currentTotalValue;
    }

    public void setCurrentTotalValue(Long currentTotalValue) {
        this.currentTotalValue = currentTotalValue;
    }

    public Long getLastTotalValue() {
        return lastTotalValue;
    }

    public void setLastTotalValue(Long lastTotalValue) {
        this.lastTotalValue = lastTotalValue;
    }

    public Double getGrowPercent() {
        return growPercent;
    }

    public void setGrowPercent(Double growPercent) {
        this.growPercent = growPercent;
    }
}
