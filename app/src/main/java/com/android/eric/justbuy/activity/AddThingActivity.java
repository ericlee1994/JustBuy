package com.android.eric.justbuy.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.eric.justbuy.R;
import com.android.eric.justbuy.model.Thing;
import com.android.eric.justbuy.sql.ThingSQL;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;


public class AddThingActivity extends AppCompatActivity {
    private static final String TAG = "AddThingActivity";
    private static final int REQUEST_CODE_PHOTO = 0x001;

    private EditText et_thing_name;
    private EditText et_thing_num;
    private EditText et_thing_unitPrice;
    private EditText et_thing_localRate;
    private EditText et_thing_convertPrice;
    private Button btn_add_thing;
    private Button btn_take_photo;
    private ImageView imageView;

    private Bitmap photo;

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
        btn_take_photo = findViewById(R.id.btn_take_photo);
        imageView = findViewById(R.id.img_thing);

        btn_add_thing.setOnClickListener(onClickListener);
        btn_take_photo.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_thing:
                    Thing thing = new Thing();
                    thing.setName(et_thing_name.getText().toString());
                    thing.setImage(bitmapToByte(photo));
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
                case R.id.btn_take_photo:

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, REQUEST_CODE_PHOTO);

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PHOTO){
            if (resultCode == RESULT_OK) {
                photo = data.getParcelableExtra("data");
                imageView.setImageBitmap(photo);
            }
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    public byte[] bitmapToByte(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }
        return null;
    }

}
