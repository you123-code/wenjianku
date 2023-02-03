package com.demo.neibulei;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/28 17:58
 */
public class NeiBuLeiSingle {
    //内部类实现单例
    public NeiBuLeiSingle() {
    }
    private static class SingleTon{
        private static NeiBuLeiSingle INSTANCE = new NeiBuLeiSingle();
    }
    public static NeiBuLeiSingle getInstance(){
        return SingleTon.INSTANCE;
    }

    public static void main(String[] args) {
        NeiBuLeiSingle instance = NeiBuLeiSingle.getInstance();
    }
}
