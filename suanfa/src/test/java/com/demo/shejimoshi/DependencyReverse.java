package com.demo.shejimoshi;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/27 22:39
 * 依赖倒转原则
 */
public class DependencyReverse {
    public static void main(String[] args) {
        //使用接口依赖传递
        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.open(new Chuangwei());
        //构造方法依赖传递
        OpenAndClose1 openAndClose1 = new OpenAndClose1(new ChangHong());
        openAndClose1.open();
        //setter方法依赖传递
        OpenAndClose2 openAndClose2 = new OpenAndClose2();
        openAndClose2.setTv(new AppleTv());
        openAndClose2.open();
    }
}
//接口实现依赖传递
interface IOpenAndClose{
    public void open(ITv iTv);
}

interface ITv{
    public void play();
}

class Chuangwei implements ITv{

    @Override
    public void play() {
        System.out.println("创维电视打开！");
    }
}

class OpenAndClose implements IOpenAndClose{

    @Override
    public void open(ITv iTv) {
        iTv.play();
    }
}
////////////////////////////////////////////
//构造方法实现依赖传递
interface IOpenAndClose1{
    public void open();
}

interface ITv1{
    public void play();
}
class ChangHong implements ITv1{

    @Override
    public void play() {
        System.out.println("长虹电视开启！");
    }
}
class OpenAndClose1 implements IOpenAndClose1{
    private ITv1 iTv1;

    public OpenAndClose1(ITv1 iTv1) {
        this.iTv1 = iTv1;
    }

    @Override
    public void open() {
        this.iTv1.play();
    }
}
////////////////////////////////
//通过Setter方法实现依赖传递
interface IOpenAndClose2{
    public void open();
    public void setTv(ITv2 iTv2);
}
interface ITv2{
    public void play();
}

class AppleTv implements ITv2{

    @Override
    public void play() {
        System.out.println("苹果电视开启！！");
    }
}

class OpenAndClose2 implements IOpenAndClose2{
    private ITv2 iTv2;
    @Override
    public void open() {
        iTv2.play();
    }

    @Override
    public void setTv(ITv2 iTv2) {
        this.iTv2 = iTv2;
    }
}