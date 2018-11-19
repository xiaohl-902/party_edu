package com.igreatstone.partyedu.model;

/**
 * Created by yy on 2017/10/19.
 */

public class PlayVideoBean {


    /**
     * play_url : http://tv.cctv.com/cctv2/
     * is_loop : false
     * width : 503
     * height : 363
     * marginLeft : 471
     * marginTop : 297
     */

    private String play_url;
    private boolean is_loop;
    private int width;
    private int height;
    private int marginLeft;
    private int marginTop;

    public String getPlay_url() {
        return play_url;
    }

    public void setPlay_url(String play_url) {
        this.play_url = play_url;
    }

    public boolean isIs_loop() {
        return is_loop;
    }

    public void setIs_loop(boolean is_loop) {
        this.is_loop = is_loop;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }
}
