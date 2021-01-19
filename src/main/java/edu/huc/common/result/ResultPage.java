package edu.huc.common.result;

import java.util.List;

public class ResultPage {
    private List data;

    private long pageSize;//总页数

    private long page;//当前页

    private long count;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
