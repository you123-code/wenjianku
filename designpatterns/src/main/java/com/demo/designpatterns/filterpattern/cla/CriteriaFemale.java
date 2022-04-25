package com.demo.designpatterns.filterpattern.cla;

import com.demo.designpatterns.filterpattern.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 11:17
 */
public class CriteriaFemale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
