package com.demo.designpatterns.filterpattern.cla;

import com.demo.designpatterns.filterpattern.Criteria;

import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 11:19
 */
public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;
    public AndCriteria(Criteria criteria,Criteria otherCriteria){
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstPersonsCriteria = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstPersonsCriteria);
    }
}
