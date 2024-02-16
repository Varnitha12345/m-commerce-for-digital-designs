package lk.jiat.app.varandesigns.modals;

import java.io.Serializable;

public class Recommended implements Serializable {
    String name;
    String description;
    String rating;
    int price;
    String category;
    String img_url;

    public Recommended() {
    }

    public Recommended(String name, String description, String rating, int price, String category, String img_url) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.category = category;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg_url() {
        return img_url;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
