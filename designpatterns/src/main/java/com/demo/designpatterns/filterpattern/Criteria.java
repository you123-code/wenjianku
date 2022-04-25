package com.demo.designpatterns.filterpattern;

import com.demo.designpatterns.filterpattern.cla.Person;

import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 11:11
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
