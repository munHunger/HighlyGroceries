package se.munhunger.highlygroceries.model;

import java.util.List;

/**
 * Created by munHunger on 2018-01-20.
 */

public class Category {
    private String category;
    private List<Item> items;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
