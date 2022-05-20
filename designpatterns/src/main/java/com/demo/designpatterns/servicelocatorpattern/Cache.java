package com.demo.designpatterns.servicelocatorpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 15:17
 */
public class Cache {
    private List<Service> services;
    public Cache(){
        services = new ArrayList<Service>();
    }

    public Service getService(String serviceName){
        for (Service service : services) {
            if(service.getName().equalsIgnoreCase(serviceName)){
                System.out.println("Returning cached  "+serviceName+" object");
                return service;
            }
        }
        return null;
    }

    public  void addService(Service newService){
        boolean exit = false;
        for (Service service : services) {
            if(service.getName().equalsIgnoreCase(newService.getName())){
                exit = true;
            }
        }
        if(!exit){
            services.add(newService);
        }
    }
}
