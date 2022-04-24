package com.demo.designpatterns.adapterpattern.cla;

import com.demo.designpatterns.adapterpattern.inter.AdvancedMediaPlayer;
import com.demo.designpatterns.adapterpattern.inter.MediaPlayer;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 16:06
 */
public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer = new VlcPlayer();
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
