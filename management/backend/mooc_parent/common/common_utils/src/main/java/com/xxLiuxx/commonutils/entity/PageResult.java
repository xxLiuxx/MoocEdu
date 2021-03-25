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

    @ApiModelProperty(value = "if has next page")
    public boolean hasNext;

    @ApiModelProperty(value = "if has previous page")
    public boolean hasPrevious;

    @ApiModelProperty(value = "current page size")
    public long currentSize;

    @ApiModelProperty(value = "total page size")
    public long totalPages;

    public PageResult(Long currentPage, Long totalRows, List<T> items, boolean hasNext, boolean hasPrevious, long currentSize, long totalPages) {
        this.currentPage = currentPage;
        this.totalRows = totalRows;
        this.items = items;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.currentSize = currentSize;
        this.totalPages = totalPages;
    }

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

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public long getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(long currentSize) {
        this.currentSize = currentSize;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

}
