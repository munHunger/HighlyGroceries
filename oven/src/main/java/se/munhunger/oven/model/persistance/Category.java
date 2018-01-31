package se.munhunger.oven.model.persistance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus Münger
 */
public class Category {
    private String category;
    private List<Item> items;

    public Category(){
        items = new ArrayList<>();
    }

    public Category(String category) {
        this();
        setCategory(category);
    }

    public Category(String category, List<Item> items) {
        this(category);
        this.items = items;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
        item.setCategory(this);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}