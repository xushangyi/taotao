package com.taotao.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */
public class DataGridResult implements Serializable{

    private static final long serialVersionUID = -6060832493150798566L;
    private Long total;
    private List<?> rows;

    public DataGridResult(Long total, List<?> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
