package kz.project.jaryq.ui.surak.models;

import java.io.Serializable;

public class Surak implements Serializable {
    String title;
    String imagePath;
    String content;
    String site_link;
    String category;

    public Surak(String title, String imagePath, String content, String site_link, String category) {
        this.title = title;
        this.imagePath = imagePath;
        this.content = content;
        this.site_link = site_link;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSite_link() {
        return site_link;
    }

    public void setSite_link(String site_link) {
        this.site_link = site_link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
