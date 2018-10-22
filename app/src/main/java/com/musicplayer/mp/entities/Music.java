package com.musicplayer.mp.entities;

public class Music {

    private String music;
    private String uri;

    public Music(String music, String uri) {
        this.music = music;
        this.uri = uri;
    }


    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    @Override
    public String toString() {
        return music+"\n"+uri;
    }
}
