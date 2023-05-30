package com.demo.zuoshe;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/15 23:07
 * 选择排序 时间复杂度O(N^2)，空间复杂度O(1)
 */
public class Code1_SelectSort {
    public static  void selectSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for(int j = i+1;j<arr.length;j++){
                minIndex = arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }
    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
