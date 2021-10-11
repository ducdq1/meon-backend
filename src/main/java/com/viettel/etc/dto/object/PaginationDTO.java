package com.viettel.etc.dto.object;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class PaginationDTO {
    Integer page;
    Integer pageSize;
    Integer totalPages;
    Integer totalElements;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String language;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
