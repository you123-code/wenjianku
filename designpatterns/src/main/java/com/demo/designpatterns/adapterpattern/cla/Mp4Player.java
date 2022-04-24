package com.demo.designpatterns.adapterpattern.cla;

import com.demo.designpatterns.adapterpattern.inter.AdvancedMediaPlayer;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 16:05
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
