package lk.jiat.app.varandesigns.modals;

public class Popular {
    String name;
    String description;
    String rating;
    String discount;
    String category;
    String img_url;

    public Popular() {
    }

    public Popular(String name, String description, String rating, String discount, String category, String img_url) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.discount = discount;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String type) {
        this.category = category;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
