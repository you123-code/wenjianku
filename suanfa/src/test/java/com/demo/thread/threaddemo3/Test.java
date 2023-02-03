package com.demo.thread.threaddemo3;

import java.util.*;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/29 18:11
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add(0,"2");
        System.out.println(list.toString());
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();
        Set<String> strings = new HashSet<>();
        strings.add("1");
        strings.add("2");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
