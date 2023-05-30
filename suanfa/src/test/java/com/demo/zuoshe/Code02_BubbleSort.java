package com.demo.zuoshe;

import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/15 23:25
 * 冒泡排序 时间复杂度O(N^2),
 */
public class Code02_BubbleSort {
    public static  void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for(int e = arr.length-1;e > 0;e--){
            for (int i = 0; i < e; i++) {
                if(arr[i] > arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
    }
    public static  void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if(i == 0){
            return;
        }
        int count = 0;
        for (int i1 = 1; i1 < i; i1++) {
            //取模加1
            if(i%i1 == 0){
                count++;
            }
        }
        if(count == 2){
            System.out.println(i+"为质数");
        }else{
            System.out.println(i+"不为质数");
        }
    }
}
