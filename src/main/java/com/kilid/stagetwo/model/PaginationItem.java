package com.kilid.stagetwo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PaginationItem<E> {
    private List<E> dataList;
    private Integer totalNumber;
    private Integer pageCount;
    private Integer pageNumber;
    private Integer pageSize;
    public PaginationItem(){}

    public PaginationItem(List<E> dataList, Integer totalNumber, Integer pageCount) {
        this.dataList = dataList;
        this.totalNumber = totalNumber;
        this.pageCount = pageCount;
    }
}