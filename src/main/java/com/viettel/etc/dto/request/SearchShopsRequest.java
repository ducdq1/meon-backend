package com.viettel.etc.dto.request;

public class SearchShopsRequest {
    private String keySearch;
    private Integer catsShopId;
    private Integer userId;

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public Integer getCatsShopId() {
        return catsShopId;
    }

    public void setCatsShopId(Integer catsShopId) {
        this.catsShopId = catsShopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
