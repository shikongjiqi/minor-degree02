package edu.huc.common.result;

import java.util.List;

public class ResultPage {
    private List data;

    private int pageSize;//总页数

    private int page;//当前页

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
