package se.munhunger.highlygroceries.model;

/**
 * Created by munHunger on 2018-01-20.
 */

public class Item {
    private String title;
    private boolean purchased;

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}