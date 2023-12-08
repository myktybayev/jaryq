package kz.project.jaryq.ui.namaz.models;

import java.io.Serializable;

public class Makala implements Serializable {
    int imageDraw;
    String title;
    String desc;

    public Makala(int imageDraw, String title, String desc) {
        this.imageDraw = imageDraw;
        this.title = title;
        this.desc = desc;
    }

    public int getImageDraw() {
        return imageDraw;
    }

    public void setImageDraw(int imageDraw) {
        this.imageDraw = imageDraw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
