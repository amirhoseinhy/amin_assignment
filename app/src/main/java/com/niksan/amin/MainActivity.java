package com.niksan.amin;


import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.niksan.amin.model.Item;
import com.niksan.amin.model.TableDA;
import java.util.List;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @BindViews({R.id.ch1, R.id.ch2, R.id.ch3, R.id.ch4, R.id.ch5, R.id.ch6,
            R.id.ch7, R.id.ch8, R.id.ch9, R.id.ch10, R.id.ch11, R.id.ch12,
            R.id.ch13, R.id.ch14, R.id.ch15, R.id.ch16, R.id.ch17, R.id.ch18,
            R.id.ch19, R.id.ch20, R.id.ch21, R.id.ch22, R.id.ch23, R.id.ch24})

    List<CheckBox> checkBoxes;

    @BindViews({R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4, R.id.txt5, R.id.txt6
            , R.id.txt7, R.id.txt8, R.id.txt9, R.id.txt10, R.id.txt11, R.id.txt12
            , R.id.txt13, R.id.txt14, R.id.txt15, R.id.txt16, R.id.txt17, R.id.txt18, R.id.txt19
            , R.id.txt20, R.id.txt21, R.id.txt22, R.id.txt23, R.id.txt24})

    List<TextView> textViews;

    TableDA itemDA;
    List<Item> items;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        checkScreenSize();
        setFont();
        itemDA = new TableDA(this);
        itemDA.open();
        initializeCheckboxes();
        updateAllCheckboxes();

    }


    private void setFont() {
        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setTypeface(Typeface.createFromAsset(MainActivity.this.getAssets(), "BKOODB.TTF"));
            textViews.get(i).setTextSize(24);
        }
    }

    private void checkScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        if (width < 1500) Toast.makeText(this, "incompatible device", Toast.LENGTH_LONG).show();
    }


    void initializeCheckboxes() {
        if (itemDA.isEmpty()) {

            for (int i = 0; i < 24; i++) {
                item = new Item();
                 item.setItemId(i);
                item.setItemStatus(0);
                itemDA.addConfig(item);
            }

        } else Log.i(TAG, "++++++ NOT EMPTY +++++++++");
    }

    void updateAllCheckboxes() {
        items = itemDA.getAllConfig();
        for (int i = 0; i < 24; i++) {
            if (items.get(i).getItemStatus() == 1) {
                checkBoxes.get(i).setChecked(true);
            } else {
                checkBoxes.get(i).setChecked(false);
            }
        }
    }


    public void save(View view) {

        for (int i = 0; i < 24; i++) {
            if (checkBoxes.get(i).isChecked()) {
                items.get(i).setItemId(i);
                items.get(i).setItemStatus(1);
                itemDA.update(items.get(i));
            } else {
                items.get(i).setItemId(i);
                items.get(i).setItemStatus(0);
                itemDA.update(items.get(i));
            }
        }
    }

    @Override
    protected void onPause() {
        itemDA.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        itemDA.open();
        super.onResume();
    }


}
