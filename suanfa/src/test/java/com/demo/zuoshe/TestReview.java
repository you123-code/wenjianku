package com.demo.zuoshe;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/17 22:14
 */
public class TestReview {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        String[] split = next.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        selectSort(ints);
    }

    public static void selectSort(int[] arr){
       //插入排序
        for (int i = 1; i < arr.length; i++) {
            for(int j = i-1;j >= 0 && arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int minIndex) {
        int tmp = arr[i];
        arr[i]  = arr[minIndex];
        arr[minIndex] = tmp;
    }
}
