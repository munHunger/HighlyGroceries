package se.munhunger.oven.model.persistance;

import java.io.Serializable;

/**
 * @author Marcus Münger
 */
public class GroceryList implements Serializable {
    private String title;
    private String id;
    private int size;

    public GroceryList() {}

    public GroceryList(String title, String id) {
        this();
        setId(id);
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}