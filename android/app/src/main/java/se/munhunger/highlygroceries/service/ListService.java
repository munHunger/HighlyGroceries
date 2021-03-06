package se.munhunger.highlygroceries.service;

import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import se.munhunger.highlygroceries.activities.GroceryList;
import se.munhunger.highlygroceries.model.Category;
import se.munhunger.highlygroceries.model.Item;
import se.munhunger.highlygroceries.model.GroceryLists;

/**
 * Created by munHunger on 2018-01-20.
 */

public class ListService {

    private static List<GroceryLists> lists = new ArrayList<>();
    static {
        lists.add(new GroceryLists("Weekly", "1"));
        lists.add(new GroceryLists("Christmas cooking", "41"));
    }

    public void addItem(Item item, String listID) {

    }

    public void createList(String name) {
        lists.add(new GroceryLists(name, UUID.randomUUID().toString()));
    }

    public List<GroceryLists> getLists() {
        return lists;
    }

    public List<Category> getList(String id) {
        AccessToken token = AccessToken.getCurrentAccessToken();
        List<Category> groceryList = new ArrayList<>();
        Category category = new Category("Test Category");
        category.addItem(new Item("Malk"));
        category.addItem(new Item(id));
        category.addItem(new Item("Onion"));
        category.addItem(new Item("Banana"));
        groceryList.add(category);

        category = new Category("Other");
        category.addItem(new Item("Weed"));
        category.addItem(new Item("Coke(powdered)"));
        category.addItem(new Item("Pills"));
        groceryList.add(category);
        return groceryList;
    }
}
