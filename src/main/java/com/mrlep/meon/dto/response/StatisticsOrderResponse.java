package com.mrlep.meon.dto.response;

import com.mrlep.meon.dto.object.StatisticsBillItem;
import com.mrlep.meon.dto.object.StatisticsOrderItem;

import java.util.List;

public class StatisticsOrderResponse {
    List<StatisticsBillItem> currentItems;
    List<StatisticsBillItem> lastItems;
    Long currentTotalValue;
    Long lastTotalValue;
    Double growPercent;

    public List<StatisticsBillItem> getCurrentItems() {
        return currentItems;
    }

    public void setCurrentItems(List<StatisticsBillItem> currentItems) {
        this.currentItems = currentItems;
    }

    public List<StatisticsBillItem> getLastItems() {
        return lastItems;
    }

    public void setLastItems(List<StatisticsBillItem> lastItems) {
        this.lastItems = lastItems;
    }

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
