package se.munhunger.highlygroceries.model;

import java.io.Serializable;

/**
 * Created by munHunger on 2018-01-20.
 */

public class GroceryLists implements Serializable {
    private String title;
    private String id;
    private int size;

    public GroceryLists() {}

    public GroceryLists(String title, String id) {
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
