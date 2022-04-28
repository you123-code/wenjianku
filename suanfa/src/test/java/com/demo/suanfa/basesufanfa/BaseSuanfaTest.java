package com.demo.suanfa.basesufanfa;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 17:30
 */
public class BaseSuanfaTest {
        /**基础算法
         *1.删除排序数组中的重复项
         */
        @Test
        public void test(){
            int[] fir = new int[]{1,2,3,3,3,4,4,5};
            if(fir == null || fir.length == 0){
                System.out.println(0);
            }
            int left = 0;
            for(int right = 1; right < fir.length; right++){
                if(fir[left] != fir[right]){
                    left++;
                    fir[left] = fir[right];
                }
            }
            for (int i = 0; i < left+1; i++) {
                System.out.println(fir[i]);;
            }
        }

        /**
         * 2.买卖股票的最佳时机 II
         */
        @Test
        public void  test2(){
            //数据准备
            //int[] prices = new int[]{1,2,3,4,5};
            int[] prices = new int[]{7,1,5,3,6,4};
            int len = prices.length;
            if(prices == null || len < 2){
                System.out.println(0);}
            int[][] dp = new int[len][2];
            //初始数据
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < len; i++) {
                dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            }
            //最后一天没有股票且利润最大
            System.out.println(dp[len-1][0]);
        }

        @Test
        public void test2a(){
            //数据准备
            int[] prices = new int[]{1,2,3,4,5};
            int len = prices.length;
            if(prices == null || len < 2){
                System.out.println(0);}
            //初始条件
            int hold = -prices[0];
            int noHold = 0;
            for (int i = 0; i < len; i++) {
                noHold = Math.max(noHold,hold+prices[i]);
                hold = Math.max(hold,noHold-prices[i]);
            }
            System.out.println(noHold);
        }

        @Test
        public void  test2b(){
            //int[] prices = new int[]{1,2,3,4,5};
            //int[] prices = new int[]{7,6,4,3,1};
            int[] prices = new int[]{7,1,5,3,6,4};
            int len = prices.length;
            if(prices == null || len < 2){
                System.out.println(0);
            }
            //初始化
            int index= 1,sum = 0;
            while(index < len){
                if(prices[index-1] <= prices[index]){
                    sum += prices[index] - prices[index-1];
                    index++;
                }else{
                    index++;
                }
            }
            System.out.println(sum);
        }

        /**
         * 3.旋转数组
         */
        @Test
        public void test3(){
            int[] leng = new int[]{1,2,3,4,5};
            //数据准备
            int j = 21;
            int len = leng.length;
            int m = j % len;
            //临时数组
            int[] temp = new int[len];
            if(m == 0){
                System.out.println(Arrays.toString(leng));
            }else{
                for (int i = 0; i < len; i++) {
                    int n = i + m - len;
                    if(n < 0){
                        temp[i + m] = leng[i];
                    }else{
                        temp[n] = leng[i];
                    }
                }
                System.out.println(Arrays.toString(temp));
            }
        }

        /**
         * 4.存在重复元素
         */
        @Test
        public void test4(){
            //int[] tem = new  int[]{1,2,3,4,5};
            int[] tem = new  int[]{1,5,3,3,5};
            Arrays.sort(tem);
            for(int i = 1;i < tem.length;i++){
                if(tem[i] == tem[i-1]){
                    System.out.println(1);
                }
            }
            System.out.println(0);
        }

        /**
         * 5.只出现一次的数字
         * 注：异或 相同=0,不同=1；
         */
        @Test
        public void test5(){
            //数据准备
            int result = 0;
            int[] tem = new int[]{1,1,2,3};
            for (int i = 0; i < tem.length; i++) {
                result ^= tem[i];
                //System.out.println(result);
            }
            System.out.println("---"+result);
        }

        @Test
        public void test5a(){
            //数据准备
            int[] tem = new int[]{1,1,2,2,3};
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < tem.length; i++) {
                if(!set.add(tem[i])){
                    set.remove(tem[i]);
                }
            }
            System.out.println(Arrays.toString(new Set[]{set}));
        }

        /**
         * 两个数组的交集 II
         */
        @Test
        public void test6(){
            //数据准备
            int[] num1 = new int[]{1,1,1,4};
            int[] num2 = new int[]{1,2,1,4,5};
            List<Integer> integerList = new ArrayList<>();
            Arrays.sort(num1);
            Arrays.sort(num2);
            int i = 0,j = 0;
            while(i < num1.length && j < num2.length){
                if(num1[i] > num2[j]){
                    j++;
                }else if(num1[i] < num2[j]){
                    i++;
                }else{
                    integerList.add(num1[i]);
                    i++;
                    j++;
                }
            }
            System.out.println(integerList.toString());
        }

        /**
         * 加一
         */
        @Test
        public void test7(){
            //int[] tem = new int[]{1,3,2};
            int[] tem = new int[]{1,9,0,9,9,9};
            int ini = tem.length-1;
            int j =  0;
            if(tem[ini]!= 9){
                tem[ini] = tem[ini] + 1;
            }else{
                //最后一位为0
                tem[ini] = 0;
                while(ini!= 1){
                    ini--;
                    if(tem[ini] != 9){
                        j++;
                        if(j == 1){
                            tem[ini] = tem[ini]+1;
                            System.out.println("中断1"+Arrays.toString(tem));
                        }
                    }else{
                        tem[ini] = 0;
                    }
                }
                int[] temLast = new int[tem.length+1];
                temLast[0] = tem[0]+1;
                System.out.println("结束"+Arrays.toString(temLast));
            }
        }

        @Test
        public void test7a(){
            //int[]digits = new int[]{1,9,1,9,9,9};
            int[]digits = new int[]{1,9,9,9,9,9};
            int length = digits.length;
            for (int i = length - 1; i >= 0; i--) {
                if (digits[i] != 9) {
                    //如果数组当前元素不等于9，直接加1
                    //然后直接返回
                    digits[i]++;
                    System.out.println(Arrays.toString(digits));
                } else {
                    //如果数组当前元素等于9，那么加1之后
                    //肯定会变为0，我们先让他变为0
                    digits[i] = 0;
                }
            }
            //除非数组中的元素都是9，否则不会走到这一步，
            //如果数组的元素都是9，我们只需要把数组的长度
            //增加1，并且把数组的第一个元素置为1即可
            int temp[] = new int[length + 1];
            temp[0] = 1;
            System.out.println(Arrays.toString(temp));
        }

        /**
         * 移动零
         */
        @Test
        public void test8(){
            int[] tem = new int[]{1,0,2,3,0,4,0,0,1};
            int j = 0;
            for (int i = 0; i < tem.length; i++) {
                if(tem[i] != 0){
                    tem[j] = tem[i];
                    j++;
                }
            }
            while(j <= tem.length-1){
                tem[j++] = 0;
            }
            System.out.println(Arrays.toString(tem));
        }

        /**
         * 两数之和
         */
        @Test
        public void test9(){
            int target = 8;
            int[] tem = new int[]{1,3,7,5};
            List<String> list = new ArrayList<>();
            int[] temp = new int[2];
            for (int i = 0; i < tem.length; i++) {
                for (int i1 = tem.length - 1; i1 >= 0; i1--) {
                    int sum = Integer.sum(tem[i], tem[i1]);
                    if(sum == target){
                        temp[0] = tem[i];
                        temp[1] = tem[i1];
                        list.add(Arrays.toString(temp));
                    }
                }
            }
            System.out.println(list.toString());
        }
        @Test
        public void test10() {
            String[][] tem = new String[9][9];
            HashSet set  = null;
            String[] temp1 = new String[]{"5","3",".",".","7",".",".",".","."};
            String[] temp2 = new String[]{"6",".",".","1","9","5",".",".","."};
            String[] temp3 = new String[]{".","9","8",".",".",".",".","6","."};
            String[] temp4 = new String[]{"8",".",".",".","6",".",".",".","3"};
            String[] temp5 = new String[]{"4",".",".","8",".","3",".",".","1"};
            String[] temp6 = new String[]{"7",".",".",".","2",".",".",".","6"};
            String[] temp7 = new String[]{".","6",".",".",".",".","2","8","."};
            String[] temp8 = new String[]{".",".",".","4","1","9",".",".","5"};
            String[] temp9 = new String[]{".",".",".",".","8",".",".","7","9"};
            List<String[]>  strings = new ArrayList<>();
            strings.add(0,temp1);
            strings.add(1,temp2);
            strings.add(2,temp3);
            strings.add(3,temp4);
            strings.add(4,temp5);
            strings.add(5,temp6);
            strings.add(6,temp7);
            strings.add(7,temp8);
            strings.add(8,temp9);
            for (int i = 0; i < 9; i++) {
                tem[i] = strings.get(i);
            }
            //针对行
            for (int i = 0; i < tem.length; i++) {
                int m = 0;
                set = new HashSet();
                for (int j = 0; j < tem[i].length; j++) {
                    if(!".".equals(tem[i][j])){
                        m++;
                        set.add(tem[i][j]);
                    }
                }
                if(set.size() != m){
                    System.out.println("中断");
                }
            }
            //针对3*3
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    set = new HashSet();
                    int h = 0;
                    for(int m = 3*i;3*i <= m && m < 3*(i+1);m++){
                        for(int n = 3*k;3*k <= n && n < 3*(k+1);n++){
                            if(!".".equals(tem[m][n])){
                                h++;
                                set.add(tem[m][n]);
                            }
                        }
                    }
                    if(set.size() != h){
                        System.out.println("中断");
                    }
                }
            }
        }
        /**
         * 旋转图像
         */
        @Test
        public void test11(){
            List<String[]> str = new ArrayList<>();
            List<String> strLast = new ArrayList<>();
            String[] str1 = new String[]{"1","2","3"};
            String[] str2 = new String[]{"4","5","6"};
            String[] str3 = new String[]{"7","8","9"};
            String[] strr = null;
            str.add(str1);
            str.add(str2);
            str.add(str3);
            for (int j = 0; j < str.get(0).length; j++) {
                strr = new String[str.get(0).length];
                int m = 0;
                for (int i = str.size(); i > 0; i--) {
                    strr[m++] =  str.get(i-1)[j];
                }
                strLast.add(Arrays.toString(strr));
            }
            System.out.println(strLast.toString());
        }

        /**
         * 旋转图像(例答)
         */
        @Test
        public void test11a(){
            int[][] tem = new int[3][3];
            int len = tem.length;
            //上下交换
            for(int i = 0;i < len/2;i++){
                int[] temp = tem[i];
                tem[i] = tem[len-1-i];
                tem[len-1-i] = temp;
            }
            //对角线交换
            for (int i = 0; i < len; i++) {
                for(int j = i + 1;j < len;j++){
                    int  temp = tem[i][j];
                    tem[i][j] = tem[j][i];
                    tem[j][i] = temp;
                }
            }
        }

        /**
         * 字符串
         */
        @Test
        public void test12(){
            String str = "hello";
            String[] strs = new String[str.length()];
            for (int length = str.length(); length > 0; length--) {
                strs[str.length()-length] = String.valueOf(str.charAt(length-1));
            }
            System.out.println(Arrays.asList(strs).toString());
        }


        @Test
        public void test12a(){
            char[] str = new char[]{'h','e','l','l','o'};
            int len = str.length;
            int left = 0;
            int right = len-1;
            while (left < right){
                swap(str,left++,right--);
            }
            System.out.println(str);
        }
        private void  swap(char[] array,int i,int j){
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        /**
         * 整数反转
         */
        @Test
        public  void test13(){
            //int init = -12378;
            int init = 120;
            int res = 0;
            int newRes = 0;
            while(init != 0){
                int t = init % 10;
                newRes = res*10+t;
                if((newRes-t)/10 != res){
                    System.out.println(0);
                }
                res = newRes;
                init = init/10;
            }
            System.out.println(res);
        }

        /**
         * 字符串中的第一个唯一字符
         */
        @Test
        public  void test14(){
            String str = "loveleetcode";
            int count[]  = new int[26];
            char[] chars = str.toCharArray();
            //统计每个字符出现的次数，
            for (int i = 0; i < str.length(); i++) {
                count[chars[i]- 'a']++;
            }
            //然后遍历字符串
            for (int i = 0; i < str.length(); i++) {
                if (count[chars[i] - 'a'] == 1) {
                    System.out.println(i);
                }
            }
            System.out.println(-1);
        }
        @Test
        public void test14a() {
            String str = "loveleetcode";
            Map<Character,Integer> map = new HashMap<>();
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                map.put(aChar,map.getOrDefault(aChar,0)+1);
            }
            for (int i = 0; i < str.length(); i++) {
                if(map.get(chars[i]) == 1){
                    System.out.println(i);
                }
            }
            System.out.println(-1);
        }

        @Test
        public void test14b(){
            String  str = "loveleetcode";
            for (int i = 0; i < str.length(); i++) {
                if(str.indexOf(str.charAt(i)) == str.lastIndexOf(str.charAt(i))){
                    System.out.println(i);
                }
            }
        }

        /**
         * 有效的字母异位词
         */
        @Test
        public void test15(){
            String str = "loveleetcode";
            String strr = "loveleecode";
            int count[] = new int[26];
            if(str.length() != strr.length()){
                System.out.println("非有效字母异位词");
            }
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i)-'a']++;
            }
            for (int i = 0; i < strr.length(); i++) {
                if(count[strr.charAt(i)-'a'] == 0){
                    System.out.println("非有效字母异位词");
                }
                count[strr.charAt(i)-'a']--;
            }
            for (int i : count) {
                if(i != 0){
                    System.out.println(i);
                }
            }
            System.out.println(-1);
        }

        @Test
        public void test15a(){
            String str = "loveleetcode";
            String strr = "loveleecode";
            char schar[] = str.toCharArray();
            char tchar[] = strr.toCharArray();
            Arrays.sort(schar);
            Arrays.sort(tchar);
            System.out.println(Arrays.equals(schar,tchar));
        }

        /**
         * 验证回文串
         */
        @Test
        public void test16(){
            String str = "lovvol";
            int left = 0;
            int right = str.length()-1;
            while(left < right){
                while(!Character.isLetterOrDigit(str.charAt(left))){
                    left++;
                }
                while (!Character.isLetterOrDigit(str.charAt(right))){
                    right--;
                }
                if(Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))){
                    System.out.println("非回文串");
                }
            }
        }

        @Test
        public void test16a(){
            String str = "9Lovvol9!!";
            String actStr = str.replaceAll("[^A-Za-z0-9]","").toLowerCase();
            String revStr = new StringBuffer(actStr).reverse().toString();
            if(actStr.equals(revStr)){
                System.out.println("为回文串");
            }
        }

        @Test
        /**
         *     public boolean isPalindrome(String s) {
         *         return isPalindromeHelper(s, 0, s.length() - 1);
         *     }
         *
         *     public boolean isPalindromeHelper(String s, int left, int right) {
         *         if (left >= right)
         *             return true;
         *         while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
         *             left++;
         *         while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
         *             right--;
         *         return Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)) && isPalindromeHelper(s, ++left, --right);
         *     }
         *
         * 作者：数据结构和算法
         * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xne8id/?discussion=BHK3ga
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
        public void test16b(){
        }

        @Test
        public void test17(){
            String str = "  -40ll";
            //String  actStr = str.replaceAll("[^A-Za-z]"|" ","");
            //System.out.println(actStr);
        }
}

