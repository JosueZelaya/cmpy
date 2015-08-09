/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.utils;

import java.util.List;

/**
 *
 * @author arch
 * @param <T> El tipo de objetos que guarda
 */
public class GridResponse<T> {
    
    private Integer page;
    private Integer total;
    private Integer records;
    private List<T> objetos;
    
//    public GridResponse(){}

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List<T> getRows() {
        return objetos;
    }

    public void setRows(List<T> objetos) {
        this.objetos = objetos;
    }
    
    
    
}
