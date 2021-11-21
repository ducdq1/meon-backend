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

    public Data(Integer id, String type, String sender) {
        this.id = id;
        this.type = type;
        this.sender = sender;
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
}