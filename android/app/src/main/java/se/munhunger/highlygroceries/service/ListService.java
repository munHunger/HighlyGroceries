package se.munhunger.highlygroceries.service;

import java.util.ArrayList;
import java.util.List;

import se.munhunger.highlygroceries.model.Category;
import se.munhunger.highlygroceries.model.Item;
import se.munhunger.highlygroceries.model.GroceryLists;

/**
 * Created by munHunger on 2018-01-20.
 */

public class ListService {

    public List<GroceryLists> getLists() {
        List<GroceryLists> lists = new ArrayList<>();
        lists.add(new GroceryLists("Weekly", "1"));
        lists.add(new GroceryLists("Christmas cooking", "41"));
        return lists;
    }

    public List<Category> getList() {
        List<Category> groceryList = new ArrayList<>();
        Category category = new Category("Test Category");
        category.addItem(new Item("Malk"));
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
