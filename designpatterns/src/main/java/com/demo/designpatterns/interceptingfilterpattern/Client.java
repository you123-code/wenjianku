package com.demo.designpatterns.interceptingfilterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 11:33
 */
public class Client {
    FilterManager  filterManager;
    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }
}
