package com.hkgroup.bus.vo;

import com.hkgroup.bus.domain.Car;

public class CarVo extends Car {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String[] ids;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
