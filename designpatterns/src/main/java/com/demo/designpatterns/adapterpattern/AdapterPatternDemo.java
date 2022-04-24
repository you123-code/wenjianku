package com.demo.designpatterns.adapterpattern;

import com.demo.designpatterns.adapterpattern.cla.AudioPlayer;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 16:39
 */
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("11","ouuo");
        audioPlayer.play("vlc","ouu");
        audioPlayer.play("mp4","ouu");
        audioPlayer.play("mp3","ouu");
    }
}
