package com.mrlep.meon.dto.request;

import java.util.List;

public class CreateBillRequest {
    private Integer billId;
    private String name;
    private Integer shopId;
    private List<Integer> tableIds;
    private List<Integer> deletedTableIds;
    private String reconfirmMessage;
    private String cancelMessage;
    private Integer status = 0;
    private Integer createUserId;
    private Integer members;
    private String description;
    private Integer totalMoney;
    private Integer isCreateByStaff;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public List<Integer> getTableIds() {
        return tableIds;
    }

    public void setTableIds(List<Integer> tableIds) {
        this.tableIds = tableIds;
    }

    public String getReconfirmMessage() {
        return reconfirmMessage;
    }

    public void setReconfirmMessage(String reconfirmMessage) {
        this.reconfirmMessage = reconfirmMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getIsCreateByStaff() {
        return isCreateByStaff;
    }

    public void setIsCreateByStaff(Integer isCreateByStaff) {
        this.isCreateByStaff = isCreateByStaff;
    }

    public List<Integer> getDeletedTableIds() {
        return deletedTableIds;
    }

    public void setDeletedTableIds(List<Integer> deletedTableIds) {
        this.deletedTableIds = deletedTableIds;
    }

    public String getCancelMessage() {
        return cancelMessage;
    }

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }
}
