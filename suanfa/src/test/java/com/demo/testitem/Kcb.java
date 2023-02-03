package com.demo.testitem;

import javax.swing.*;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/12/27 22:56
 */
public class Kcb {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JTable jTable = new JTable(new KcbData());
        JScrollPane pane = new JScrollPane(jTable);
        jFrame.add(pane);
        jFrame.pack();
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

}
