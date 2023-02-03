package com.demo.IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/30 8:02
 */
public class IoTest {
    public static void main(String[] args) throws Exception {
        String str = "中国人";
		//FileOutputStream fos  = new FileOutputStream("E:/tmp/2.txt");
		//fos.write(str.getBytes("UTF-8"));
		//fos.close();
		FileWriter fw = new FileWriter("E:/tmp/2.txt");
		fw.write(str);
		fw.close();
        //PrintWriter pw = new PrintWriter("E:/tmp/2.txt","utf-8");
        //pw.write(str);
        //pw.close();

		/*FileReader fr = new FileReader("1.txt");
		char[] buf = new char[1024];
		int len = fr.read(buf);
		String myStr = new String(buf,0,len);
		System.out.println(myStr);*/
		/*FileInputStream fr = new FileInputStream("1.txt");
		byte[] buf = new byte[1024];
		int len = fr.read(buf);
		String myStr = new String(buf,0,len,"UTF-8");
		System.out.println(myStr);*/
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("1.txt"),"UTF-8"
                )
        );
        String myStr = br.readLine();
        br.close();
        System.out.println(myStr);
    }
}
