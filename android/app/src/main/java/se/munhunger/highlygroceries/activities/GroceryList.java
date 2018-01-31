package se.munhunger.highlygroceries.activities;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import se.munhunger.highlygroceries.R;
import se.munhunger.highlygroceries.admob.AdMob;
import se.munhunger.highlygroceries.fragments.NewItemFragment;
import se.munhunger.highlygroceries.fragments.NewListFragment;
import se.munhunger.highlygroceries.model.Category;
import se.munhunger.highlygroceries.model.GroceryLists;
import se.munhunger.highlygroceries.model.Item;
import se.munhunger.highlygroceries.service.ListService;

/**
 * Created by munHunger on 2018-01-20.
 */

public class GroceryList extends AppCompatActivity {

    public static final String LIST_INTENT = GroceryList.class.getCanonicalName() + ".list";

    private static final int ITEM_REMOVAL_DELAY = 300;

    private ListService listService = new ListService();

    private boolean showRemoved = false;

    private List<Category> groceryList;

    private GroceryLists groceryListParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);
        groceryListParent= (GroceryLists) getIntent().getSerializableExtra(LIST_INTENT);
        this.groceryList = listService.getList(groceryListParent.getId());
        rebuildLists();
        MobileAds.initialize(this, AdMob.ADMOB_APP_ID);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void addItem(View view) {

        NewItemFragment dialog = new NewItemFragment();
        dialog.show(getFragmentManager(), "NewItemFragment");
        dialog.setDialogListener(new NewItemFragment.DialogListener() {
            @Override
            public void onDialogPositiveClick(DialogFragment dialog, String message) {
                listService.addItem(new Item(message), groceryListParent.getId());
                rebuildLists();
            }

            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {

            }
        });
    }

    private void rebuildLists() {
        LinearLayout groceryList = findViewById(R.id.groceryList);
        LinearLayout removedList = findViewById(R.id.removedList);
        groceryList.removeAllViews();
        removedList.removeAllViews();

        buildList(groceryList, filterPurchased(this.groceryList, false));
        if(this.showRemoved)
            buildList(removedList, filterPurchased(this.groceryList, true));
    }

    private List<Category> filterPurchased(List<Category> categoryList, boolean purchased) {
        List<Category> filteredList = new ArrayList<>();
        for(Category category : categoryList) {
            List<Item> filteredItems = new ArrayList<>();
            for(Item item : category.getItems())
                if(item.isPurchased() == purchased)
                    filteredItems.add(item);
            if(!filteredItems.isEmpty())
                filteredList.add(new Category(category.getCategory(), filteredItems));
        }
        return filteredList;
    }

    public void toggleShowRemoved(View view) {
        Button button = findViewById(R.id.toggleRemoved);
        showRemoved = !showRemoved;
        button.setText(showRemoved ? R.string.hide_removed : R.string.show_removed);
        rebuildLists();
    }

    private void buildList(final LinearLayout linearLayout, List<Category> categoryList) {
        for(Category category : categoryList) {
            TextView textView = new TextView(this);
            textView.setText(category.getCategory());
            linearLayout.addView(textView);
            for(final Item item : category.getItems()) {
                final CheckBox checkBox = new CheckBox(this);
                checkBox.setChecked(item.isPurchased());
                checkBox.setText(item.getTitle());
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        item.setPurchased(isChecked);
                        TimerTask removeTask = new TimerTask() {
                            @Override
                            public void run() {
                                for(int i = 0; i < 20; i++) {
                                    try {
                                        Thread.sleep(20);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkBox.setAlpha(checkBox.getAlpha() - 0.05f);
                                        }
                                    });
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        rebuildLists();
                                    }
                                });
                            }
                        };
                        new Timer().schedule(removeTask, ITEM_REMOVAL_DELAY);
                    }
                });
                linearLayout.addView(checkBox);
            }
        }
    }
}
