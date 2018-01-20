package se.munhunger.highlygroceries.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import se.munhunger.highlygroceries.R;
import se.munhunger.highlygroceries.model.GroceryLists;

/**
 * Created by munHunger on 2018-01-20.
 */

public class GroceryListItemView extends LinearLayout {

    private GroceryLists groceryList;

    public GroceryListItemView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_view, this, true);
    }

    public GroceryLists getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(GroceryLists groceryList) {
        this.groceryList = groceryList;
        TextView label = findViewById(R.id.list_name);
        label.setText(groceryList.getTitle());
        TextView articleCount = findViewById(R.id.article_count);
        articleCount.setText(String.valueOf(groceryList.getSize()));
    }
}
