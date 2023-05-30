package com.demo.zuoshe;

import lombok.Data;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/21 18:37
 */
@Data
public class B {
    private Integer id;
    private String name;
    private String parentId;
    private String path;

    public static void main(String[] args) {
        System.out.println(d());
    }

    public static int a() {
        try {
            System.out.println("业务代码");
            int b = 2 / 0;
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行费蓝了");
        }
        return 1;
    }

    public static void b() {
        //标号
        ok:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + "j=" + j);
                if (j == 2) {
                    break ok;
                }
            }
        }
    }

    public static void c() {
    int arr[][] = {{1, 2, 3}, {4,5}};
    boolean found = false;
        for (int i = 0; i < arr.length && !found; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 2){
                    found = true;
                    break;
                }
            }
        }
    }

    public static int d(){
        {
            int x = 1;
            try
            {
                return x;
            }
            finally
            {
                ++x;
                return x;
            }
        }
    }
}
