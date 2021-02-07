package com.common.doman;

import java.util.List;

public class PageResult<T>{
    private Long total; // 总条数
    private List<T> content; //数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> content) {
        this.total = total;
        this.content = content;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
