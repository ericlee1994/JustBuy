package com.android.eric.justbuy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.eric.justbuy.R;
import com.android.eric.justbuy.model.Thing;
import com.android.eric.justbuy.sql.ThingSQL;

import java.text.SimpleDateFormat;


public class AddThingActivity extends AppCompatActivity {
    private static final String TAG = "AddThingActivity";

    public EditText et_thing_name;
    public EditText et_thing_num;
    public EditText et_thing_unitPrice;
    public EditText et_thing_localRate;
    public EditText et_thing_convertPrice;
    public Button btn_add_thing;

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thing);
        context = this;
        et_thing_name = findViewById(R.id.et_thing_name);
        et_thing_num = findViewById(R.id.et_thing_num);
        et_thing_unitPrice = findViewById(R.id.et_thing_unitPrice);
        et_thing_localRate = findViewById(R.id.et_thing_localRate);
        et_thing_convertPrice = findViewById(R.id.et_thing_convertPrice);
        btn_add_thing = findViewById(R.id.btn_add_thing);

        btn_add_thing.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_thing:
                    Thing thing = new Thing();
                    thing.setName(et_thing_name.getText().toString());
                    thing.setNumber(Integer.parseInt(et_thing_num.getText().toString()));
                    thing.setUnitPrice(Float.parseFloat(et_thing_unitPrice.getText().toString()));
                    thing.setLocalRate(Float.parseFloat(et_thing_localRate.getText().toString()));
                    thing.setPurchaseTime(SimpleDateFormat.getDateTimeInstance().format(System.currentTimeMillis()));
                    ThingSQL.getInstance(context).add(thing);

                    Intent intent = new Intent();
                    intent.putExtra("thing", thing);
                    setResult(RESULT_OK, intent);

                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }


}
