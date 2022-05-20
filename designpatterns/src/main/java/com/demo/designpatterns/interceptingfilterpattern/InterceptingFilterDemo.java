package com.demo.designpatterns.interceptingfilterpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 11:37
 * 拦截过滤器模式
 */
public class InterceptingFilterDemo {
    public static void main(String[] args) {
        Client client = new Client();
        FilterManager  filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        client.setFilterManager(filterManager);
        client.sendRequest("home");
    }
}
