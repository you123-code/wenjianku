package com.demo.suanfa.simplelinkedlist;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        System.out.println("myLinkedList.get(0) = " + myLinkedList.get(0));
        myLinkedList.addAtIndex(0,1);
        myLinkedList.addAtIndex(1,2);
        myLinkedList.addAtIndex(2,3);
        myLinkedList.addAtIndex(3,4);
        myLinkedList.deleteAtIndex(1);
        myLinkedList.addAtIndex(3,5);
        System.out.println("myLinkedList.get(1) = " + myLinkedList.get(1));
    }
}