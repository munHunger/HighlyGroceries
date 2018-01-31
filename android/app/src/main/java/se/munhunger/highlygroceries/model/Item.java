package se.munhunger.highlygroceries.model;

/**
 * Created by munHunger on 2018-01-20.
 */

public class Item {
    private String title;
    private boolean purchased;
    private Category category;

    public Item(){
        setPurchased(false);
    }

    public Item(String title) {
        this();
        setTitle(title);
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}