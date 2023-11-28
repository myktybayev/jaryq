package kz.project.jaryq.ui.kitaptar.models;

import java.util.List;

public class KitapCategory {
    String categoryName;
    List<Kitap> kitapList;

    public KitapCategory(String categoryName, List<Kitap> kitapList) {
        this.categoryName = categoryName;
        this.kitapList = kitapList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Kitap> getKitapList() {
        return kitapList;
    }

    public void setKitapList(List<Kitap> kitapList) {
        this.kitapList = kitapList;
    }
}
