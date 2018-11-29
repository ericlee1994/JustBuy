package com.android.eric.justbuy.activity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.eric.justbuy.R;
import com.android.eric.justbuy.adpater.ThingsAdapter;
import com.android.eric.justbuy.model.Thing;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    public ListView lv_Things;
    public Button btn_to_add;
    public Context context;
    public ThingsAdapter thingsAdapter;
    public ArrayList<Thing> things;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;
        lv_Things = findViewById(R.id.lv_things);
        btn_to_add = findViewById(R.id.btn_to_Add);

        btn_to_add.setOnClickListener(onClickListener);

        things = new ArrayList<>();

        thingsAdapter = new ThingsAdapter(this, things);
        lv_Things.setAdapter(thingsAdapter);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
        if (thingsAdapter != null) {
            thingsAdapter.updateView(things);
            thingsAdapter.notifyDataSetChanged();
        }else {
            Log.e(TAG, "onResume: thingsAdapter = null");
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_to_Add:
                    Intent intent = new Intent(context, AddThingActivity.class);
                    startActivityForResult(intent, 1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            Thing thing = data.getParcelableExtra("thing");
            things.add(thing);
            Log.i(TAG, "onActivityResult: things size," + things.size());
            if (thingsAdapter != null) {
                thingsAdapter.updateView(things);
                thingsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
