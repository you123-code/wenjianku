package com.demo.designpatterns.adapterpattern.inter;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 16:02
 */
public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}
