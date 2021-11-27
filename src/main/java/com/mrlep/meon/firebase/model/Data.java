package com.mrlep.meon.firebase.model;

public class Data {
    public static String BILL_STATUS_CHANGE = "BILL_STATUS_CHANGE";
    public static String ORDER_STATUS_CANCEL = "ORDER_STATUS_CANCEL";
    public static String ORDER_STATUS_RECONFIRM = "ORDER_STATUS_RECONFIRM";
    public static String ORDER_STATUS_DONE = "ORDER_STATUS_DONE";
    public static String ORDER_STATUS_REJECT = "ORDER_STATUS_REJECT";

    Integer id;
    String type;
    String sender;
    String click_action = "FLUTTER_NOTIFICATION_CLICK";
    Integer billStatus;
    String billName;
    String createDate;

    public Data(Integer id, String type, String sender,Integer billStatus,String billName,String createDate) {
        this.id = id;
        this.type = type;
        this.sender = sender;
        this.billStatus = billStatus;
        this.billName = billName;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getClick_action() {
        return click_action;
    }

    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}