package lk.jiat.app.varandesigns.modals;

import java.io.Serializable;

public class Order implements Serializable {
    String designName;
    String designPrice;
    String currentDate;
    String currentTime;
    int totalPrice;
    String documentId;

    public Order() {
    }

    public Order(String designName, String designPrice, String currentDate, String currentTime, int totalPrice, String documentId) {
        this.designName = designName;
        this.designPrice = designPrice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalPrice = totalPrice;
        this.documentId = documentId;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    public String getDesignPrice() {
        return designPrice;
    }

    public void setDesignPrice(String designPrice) {
        this.designPrice = designPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
