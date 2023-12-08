package kz.project.jaryq.ui.namaz.models;

import java.io.Serializable;

public class Namaz implements Serializable {
    int imageDraw;
    String title;
    String desc;
    String content;
    String videoId;

    public Namaz(int imageDraw, String title, String desc, String content, String videoId) {
        this.imageDraw = imageDraw;
        this.title = title;
        this.desc = desc;
        this.content = content;
        this.videoId = videoId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
