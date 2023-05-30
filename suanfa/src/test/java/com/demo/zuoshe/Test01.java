package com.demo.zuoshe;


import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/16 20:27
 */
public class Test01 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String[] split = s.split(",");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        process1(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void process(Map<String, A> collect){

    }
    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i]  = arr[j];
        arr[j]  = tmp;
    }
    /**
     * 两个数出现奇数次，其他数出现偶数次，求两个出现奇数次的数分别是
     */
    public static void test01(int[] arr){
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        //eor = a ^ b;
        //eor有一位必然为1
        //获取最右侧的1
        int rightOne = eor & (~eor+1);
        //定义eor'=0,找出a or b
        int onlyOne = 0;
        for (int cur : arr) {
            //找出所有该位含1的数（包含a or b）
            if((cur & rightOne) == 1){
                onlyOne ^= cur;//等到a or b
            }
        }
        System.out.println(onlyOne + "   "+ (onlyOne ^  eor));
    }

    /**
     * 插入排序,时间复杂度O(N^2)
     * 优于选择排序 和冒泡排序
     */
    public static void test02(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for(int j = i-1;j>=0 && arr[j] > arr[j+1];j--){
                    swap(arr,j,j+1);
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 有序数组找一个数是否存在（以2为底N的对数，logN）
     * 找出>=n的最左侧的那个数（有序，logN）
     * 局部最小值问题（无序，且相邻两值不相等）
     */

    /**
     * 递归找出最大值
     */
    public static int getMax(int[] arr) {
         return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int left,int right){
        if(left == right){
            return arr[left];
        }
        int mid = left + ((right-left) >> 1);
        int leftMax = process(arr,left,mid);
        int rightMax = process(arr,mid+1,right);
        return Math.max(leftMax,rightMax);
    }

    /**
     * 归并排序 O(NlogN)
     */
    public static void process1(int[] arr,int left,int right){
        if(left == right){
            return;
        }
        int mid = left+((right-left)>>1);
        process1(arr,left,mid);
        process1(arr,mid+1,right);
        merge(arr,left,mid,right);
    }
    public static void merge(int[] arr,int left,int mid,int right){
        int[] help = new int[right-left+1];
        int p1 = left;
        int p2 = mid+1;
        int p3 = 0;
        while (p1<=mid && p2<=right){
            help[p3++] = arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1 <= mid){
            help[p3++] = arr[p1++];
        }
        while (p2 <= right){
            help[p3++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[i+left] = help[i];
        }
    }
}
