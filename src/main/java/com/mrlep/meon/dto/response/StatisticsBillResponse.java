package com.mrlep.meon.dto.response;

import com.mrlep.meon.dto.object.StatisticsBillItem;

import java.util.Date;
import java.util.List;

public class StatisticsBillResponse {
    private Long totalMoney;
    private Long totalBill;
    List<StatisticsBillItem> items;


    public List<StatisticsBillItem> getItems() {
        return items;
    }

    public void setItems(List<StatisticsBillItem> items) {
        this.items = items;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Long totalBill) {
        this.totalBill = totalBill;
    }
}
