package se.munhunger.highlygroceries.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import se.munhunger.highlygroceries.R;
import se.munhunger.highlygroceries.model.Category;
import se.munhunger.highlygroceries.model.Item;
import se.munhunger.highlygroceries.service.ListService;

/**
 * Created by munHunger on 2018-01-20.
 */

public class GroceryList extends AppCompatActivity {

    private ListService listService = new ListService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.groceryList);

        List<Category> groceryList = listService.getList();
        for(Category category : groceryList) {
            TextView textView = new TextView(this);
            textView.setText(category.getCategory());
            linearLayout.addView(textView);
            for(Item item : category.getItems()) {
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(item.getTitle());
                linearLayout.addView(checkBox);
            }
        }
    }
}
