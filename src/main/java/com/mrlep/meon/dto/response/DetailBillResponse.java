package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import com.mrlep.meon.utils.Constants;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetailBillResponse {
    Integer billId;
    Integer shopId;
    String shopAvatar;
    String shopName;
    String shopAddress;
    String shopPhone;
    String billName;
    Integer billStatus;
    String description;
    Integer totalMoney;
    String userName;
    String phone;
    String avatar;
    Double vat;
    Integer vatMoney;
    String subMoneyDescription;
    Integer subMoney;
    Integer preMoney;
    Integer discountMoney;
    String billFile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer numberMembers;
    Integer createUserId;
    Integer numberOrders;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String reconfirmMessage;

    String cancelMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> tablesName;

    String createDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<OrderItem> orderItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<BillTablesItem> tables;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<BillMembersItem> members;

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Integer getNumberMembers() {
        return numberMembers;
    }

    public void setNumberMembers(Integer numberMembers) {
        this.numberMembers = numberMembers;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getReconfirmMessage() {
        return reconfirmMessage;
    }

    public void setReconfirmMessage(String reconfirmMessage) {
        this.reconfirmMessage = reconfirmMessage;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShopAvatar() {
        return shopAvatar;
    }

    public void setShopAvatar(String shopAvatar) {
        this.shopAvatar = shopAvatar;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<BillTablesItem> getTables() {
        return tables;
    }

    public void setTables(List<BillTablesItem> tables) {
        this.tables = tables;
    }

    public List<BillMembersItem> getMembers() {
        return members;
    }

    public void setMembers(List<BillMembersItem> members) {
        this.members = members;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCancelMessage() {
        return cancelMessage;
    }

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    public List<String> getTablesName() {
        return tablesName;
    }

    public void setTablesName(List<String> tablesName) {
        this.tablesName = tablesName;
    }

    public Integer getNumberOrders() {
        return numberOrders;
    }

    public void setNumberOrders(Integer numberOrders) {
        this.numberOrders = numberOrders;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Integer getVatMoney() {
        if(vatMoney == null){
            return 0;
        }
        return vatMoney;
    }

    public void setVatMoney(Integer vatMoney) {
        this.vatMoney = vatMoney;
    }

    public String getSubMoneyDescription() {
        return subMoneyDescription;
    }

    public void setSubMoneyDescription(String subMoneyDescription) {
        this.subMoneyDescription = subMoneyDescription;
    }

    public Integer getSubMoney() {
        if(subMoney == null){
            return 0;
        }
        return subMoney;
    }

    public void setSubMoney(Integer subMoney) {
        this.subMoney = subMoney;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public Integer getPreMoney() {
        if(preMoney == null){
            return 0;
        }
        return preMoney;
    }

    public void setPreMoney(Integer preMoney) {
        this.preMoney = preMoney;
    }

    public String getBillFile() {
        if (billFile != null) {
            return Constants.DOMAIN + billFile;
        }
        return billFile;
    }

    public void setBillFile(String billFile) {
        this.billFile = billFile;
    }

    public Integer getDiscountMoney() {
        if(discountMoney == null){
            return 0;
        }
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }
}
