package com.android.eric.justbuy.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.eric.justbuy.R;
import com.android.eric.justbuy.model.Thing;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ThingsAdapter extends BaseAdapter {

    private ArrayList<Thing> things;
    private LayoutInflater layoutInflater;

    public ThingsAdapter(Context context, ArrayList<Thing> things) {
        this.things = things;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return things.size();
    }

    @Override
    public Object getItem(int position) {
        return things.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_thing, parent ,false);
            viewHolder = new ViewHolder();
            viewHolder.img_thing = convertView.findViewById(R.id.img_thing);
            viewHolder.tv_thing_name = convertView.findViewById(R.id.tv_thing_name);
            viewHolder.tv_thing_num = convertView.findViewById(R.id.tv_thing_num);
            viewHolder.tv_thing_unitPrice = convertView.findViewById(R.id.tv_thing_unitPrice);
            viewHolder.tv_thing_localRate = convertView.findViewById(R.id.tv_thing_localRate);
            viewHolder.tv_thing_totalPrice = convertView.findViewById(R.id.tv_thing_totalPrice);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Thing thing = things.get(position);
        viewHolder.tv_thing_name.setText(thing.getName());
        viewHolder.tv_thing_num.setText(String.valueOf(thing.getNumber()));
        viewHolder.tv_thing_unitPrice.setText(String.valueOf(thing.getUnitPrice()));
        viewHolder.tv_thing_localRate.setText(String.valueOf(thing.getLocalRate()));
        viewHolder.tv_thing_totalPrice.setText(formatNumber(calculateTotal(thing.getUnitPrice(), thing.getNumber(), thing.getLocalRate())));

        return convertView;
    }

    private class ViewHolder {
        ImageView img_thing;
        TextView tv_thing_name;
        TextView tv_thing_num;
        TextView tv_thing_unitPrice;
        TextView tv_thing_localRate;
        TextView tv_thing_totalPrice;
    }

    private float calculateTotal(float unitPrice, int num, float localRate) {
        return unitPrice * num * localRate;
    }

    public void updateView(ArrayList<Thing> things) {
        this.things = things;
    }

    private String formatNumber(float number) {
        NumberFormat numberFormat = new DecimalFormat("##.##");
        return numberFormat.format(number);
    }
}
