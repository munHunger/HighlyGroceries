package se.munhunger.highlygroceries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.groceryList);
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText("Random checkbox");
        linearLayout.addView(checkBox);
    }
}
