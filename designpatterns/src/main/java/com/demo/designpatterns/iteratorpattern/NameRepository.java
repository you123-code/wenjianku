package com.demo.designpatterns.iteratorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/29 10:42
 * 迭代器模式
 */
public class NameRepository implements Container {
    public String[] names = {"Robert","John","Julie","Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }
    private class NameIterator implements Iterator{
        int index;
        @Override
        public boolean hasNext() {
            if(index < names.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
