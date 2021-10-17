package com.viettel.etc.dto.request;

public class SearchMenusRequest {
    private String keySearch;
    private Integer menuGroupId;
    private String orderBy;
    private Integer startRecord = 0 ;
    private Integer pageSize = 10;
    private Double lat;
    private Double lng;

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public Integer getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(Integer menuGroupId) {
        this.menuGroupId = menuGroupId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
}
