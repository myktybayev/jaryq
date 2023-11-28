package kz.project.jaryq.ui.kitaptar.models;

import java.io.Serializable;

public class Kitap implements Serializable {
    String imagePath;
    String bookName;
    String bookAuthor;
    String bookDesc;
    String bookLink;
    String category;

    public Kitap(String imagePath, String bookName, String bookAuthor, String bookDesc, String bookLink, String category) {
        this.imagePath = imagePath;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDesc = bookDesc;
        this.bookLink = bookLink;
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
