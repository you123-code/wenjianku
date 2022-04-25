package com.demo.designpatterns.filterpattern.cla;

import com.demo.designpatterns.filterpattern.Criteria;

import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 11:38
 */
public class OrCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;
    public OrCriteria(Criteria criteria,Criteria otherCriteria){
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstPersonsCriteria = criteria.meetCriteria(persons);
        List<Person> otherPersonsCriteria = otherCriteria.meetCriteria(persons);
        for (Person person : otherPersonsCriteria) {
            if(!firstPersonsCriteria.contains(person))
                firstPersonsCriteria.add(person);
        }
        return firstPersonsCriteria;
    }
}
