package com.demo.suanfa.simplelinkedlist;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 18:01
 */
public class MyLinkedList {
    int size;
    ListCode head;
    //初始化
    public MyLinkedList(){
        size = 0;
        head = new ListCode(0);
    }
    //获取指定下标val
    public int get(int index){
        if(index < 0 || index > size){
            return -1;
        }else{
            ListCode curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.val;
        }
    }
    public void addATHead(int val){
        addAtIndex(0,val);
    }
    public void addTail(int val){
        addAtIndex(size,val);
    }
    public void addAtIndex(int index,int val){
         if(index > size){
             return;
         }
        //index小于0，插入到表头
        if(index < 0){
            index = 0;
        }
        //元素+1
        ++size;
        ListCode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListCode toAdd = new ListCode(val);
        toAdd.next = pre.next;
        pre.next = toAdd;
    }
    public void deleteAtIndex(int index){
        if(index > size || index <0){
            return;
        }
        ListCode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
            pre.next = pre.next.next;
    }
}
