package se.munhunger.highlygroceries.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import se.munhunger.highlygroceries.R;
import se.munhunger.highlygroceries.admob.AdMob;
import se.munhunger.highlygroceries.fragments.NewListFragment;
import se.munhunger.highlygroceries.views.GroceryListItemView;
import se.munhunger.highlygroceries.model.GroceryLists;
import se.munhunger.highlygroceries.service.ListService;

/**
 * Created by munHunger on 2018-01-20.
 */

public class ListView extends AppCompatActivity {

    private ListService listService = new ListService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        rebuildList();
        MobileAds.initialize(this, AdMob.ADMOB_APP_ID);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void rebuildList() {
        final ListView context = this;
        LinearLayout list = findViewById(R.id.list);
        list.removeAllViews();
        List<GroceryLists> lists = listService.getLists();
        for(final GroceryLists groceryList : lists) {
            GroceryListItemView groceryListItemView = new GroceryListItemView(context);
            groceryListItemView.setGroceryList(groceryList);
            list.addView(groceryListItemView);
            groceryListItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GroceryList.class);
                    intent.putExtra(GroceryList.LIST_INTENT, groceryList);
                    startActivity(intent);
                }
            });
        }
    }

    public void addList(View view) {
        NewListFragment dialog = new NewListFragment();
        dialog.show(getFragmentManager(), "NewListFragment");
        dialog.setDialogListener(new NewListFragment.DialogListener() {
            @Override
            public void onDialogPositiveClick(DialogFragment dialog) {
                rebuildList();
            }

            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {

            }
        });
    }
}
