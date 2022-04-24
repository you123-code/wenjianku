package com.demo.designpatterns.adapterpattern.cla;

import com.demo.designpatterns.adapterpattern.inter.AdvancedMediaPlayer;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 16:04
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
