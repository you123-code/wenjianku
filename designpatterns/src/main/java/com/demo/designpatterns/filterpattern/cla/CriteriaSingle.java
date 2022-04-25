package com.demo.designpatterns.filterpattern.cla;

import com.demo.designpatterns.filterpattern.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 11:18
 */
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons  = new ArrayList<>();
        for (Person person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons .add(person);
            }
        }
        return singlePersons ;
    }
}
