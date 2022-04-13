package com.demo.testdatasource.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/13 12:20
 */
@Getter
@Setter
@NoArgsConstructor
public class Pagination<T> implements Serializable {
    private int page;

    private int pageSize;

    private int total;

    private int totalPage;

    private List<T> list;

    private static final long serialVersionUID = 1L;

    public Pagination(List<T> list, int page, int pageSize, int total) {
        this.totalPage = (int) Math.ceil(total / (float) pageSize);
        if (this.totalPage == 0) {
            this.totalPage = 1;
        }
        this.page = Math.min(page, this.totalPage);
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }
}
