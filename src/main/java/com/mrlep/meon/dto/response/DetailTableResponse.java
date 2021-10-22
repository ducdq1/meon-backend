package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import com.mrlep.meon.repositories.tables.entities.ShopEntity;
import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailTableResponse {
    private ShopTableEntity table;
    private BillEntity billInfo;

    public ShopTableEntity getTable() {
        return table;
    }

    public void setTable(ShopTableEntity table) {
        this.table = table;
    }

    public BillEntity getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(BillEntity billInfo) {
        this.billInfo = billInfo;
    }
}
