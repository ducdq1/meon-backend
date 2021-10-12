package com.viettel.etc.dto.request;

public class CreateShopRequest {
    private String name;
    private String address;
    private String phone;
    private Integer catsShopId;
    private Double lat;
    private Double lng;
    private Integer createUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCatsShopId() {
        return catsShopId;
    }

    public void setCatsShopId(Integer catsShopId) {
        this.catsShopId = catsShopId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}
