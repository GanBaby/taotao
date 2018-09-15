package com.taotao.common.pojo;

import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-9-15 下午 18:46
 */
public class EUDateGridResult {

    //数据总条数
    private long total;
    //？的意思是这个list中的泛型是任意类型
    //数据集
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
