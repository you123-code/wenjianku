package com.demo.zuoshe;

import lombok.Data;

import java.io.*;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/21 18:37
 */
@Data
public class A implements Serializable,Cloneable {
    private Integer id;
    private String name;
    private String parentId;

    public A(Integer id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        A a  = new A(1,"ChangSan","2");
        A b = a;
        A clone = (A) a.clone();
        System.out.println(a);
        System.out.println(clone);
        System.out.println("a和clone对比:"+(a == clone));
        System.out.println("a和b对比:"+(a == b));
        System.out.println("下面是深拷贝------------------------");
        try {
            //写入字节流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(outputStream);
            obs.writeObject(a);
            obs.close();
            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInput);
            A c = (A)inputStream.readObject();
            System.out.println("深拷贝后："+(a==c));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("下面IO的序列化和反序列化操作--------");
        File file = new File("D:\\test.txt");
        try {
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));
            objOutput.writeObject(a);
            objOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件反序列化为对象---------------------");
        try {
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));
            A o = (A)objInput.readObject();
            System.out.println("反序列化后的结果："+o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
