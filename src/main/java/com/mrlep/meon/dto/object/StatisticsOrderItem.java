package com.mrlep.meon.dto.object;

public class StatisticsOrderItem {
    Integer currentValue;
    Integer lastValue;

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getLastValue() {
        return lastValue;
    }

    public void setLastValue(Integer lastValue) {
        this.lastValue = lastValue;
    }
}
