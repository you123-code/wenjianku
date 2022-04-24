package com.demo.designpatterns.adapterpattern.cla;

import com.demo.designpatterns.adapterpattern.inter.MediaPlayer;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 16:34
 */
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("fileName = " + fileName);
        }else if(audioType.equals("vlc") || audioType.equals("mp4")){
            mediaAdapter  = new MediaAdapter(audioType);
            mediaAdapter.play(audioType,fileName);
        }else{
            System.out.println("不支持的播放格式");
        }
    }
}
