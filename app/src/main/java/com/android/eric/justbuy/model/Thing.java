package com.android.eric.justbuy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Thing implements Parcelable {
    private byte[] image;
    private String name;
    private int number;
    private float tax;
    private float unitPrice;
    private float localRate;
    private float discount;
    private String purchaseTime;


    public Thing(){
        number = 0;
        tax = 0;
        unitPrice = 0;
        localRate = 0;
        discount = 0;
    }

    protected Thing(Parcel in) {
        image = in.createByteArray();
        name = in.readString();
        number = in.readInt();
        tax = in.readFloat();
        unitPrice = in.readFloat();
        localRate = in.readFloat();
        discount = in.readFloat();
        purchaseTime = in.readString();
    }

    public static final Creator<Thing> CREATOR = new Creator<Thing>() {
        @Override
        public Thing createFromParcel(Parcel in) {
            return new Thing(in);
        }

        @Override
        public Thing[] newArray(int size) {
            return new Thing[size];
        }
    };

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getLocalRate() {
        return localRate;
    }

    public void setLocalRate(float localRate) {
        this.localRate = localRate;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(image);
        dest.writeString(name);
        dest.writeInt(number);
        dest.writeFloat(tax);
        dest.writeFloat(unitPrice);
        dest.writeFloat(localRate);
        dest.writeFloat(discount);
        dest.writeString(purchaseTime);
    }
}
