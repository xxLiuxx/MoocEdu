package com.xxLiuxx.commonutils.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Yuchen Liu
 */
@ApiModel("Page Result")
public class PageResult<T> {
    @ApiModelProperty(value = "current page number")
    public Long currentPage;

    @ApiModelProperty(value = "total rows of result")
    public Long totalRows;

    @ApiModelProperty(value = "item list for current search")
    public List<T> items;

    public PageResult(Long currentPage, Long totalRows, List<T> items) {
        this.currentPage = currentPage;
        this.totalRows = totalRows;
        this.items = items;
    }

    public PageResult(Long totalRows, List<T> items) {
        this.totalRows = totalRows;
        this.items = items;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
