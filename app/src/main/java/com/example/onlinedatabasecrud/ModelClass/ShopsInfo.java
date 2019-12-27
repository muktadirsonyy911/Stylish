package com.example.onlinedatabasecrud.ModelClass;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;

public class ShopsInfo implements Parcelable
{
    private String shopName;
    private String shopAddress;
    private String shopPhone;

    public ShopsInfo() {
    }

    public ShopsInfo(String shopName, String shopAddress, String shopPhone) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopPhone = shopPhone;
    }

    protected ShopsInfo(Parcel in) {
        shopName = in.readString();
        shopAddress = in.readString();
        shopPhone = in.readString();
    }

    public static final Creator<ShopsInfo> CREATOR = new Creator<ShopsInfo>() {
        @Override
        public ShopsInfo createFromParcel(Parcel in) {
            return new ShopsInfo(in);
        }

        @Override
        public ShopsInfo[] newArray(int size) {
            return new ShopsInfo[size];
        }
    };

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    @Override
    public String toString() {
        return "ShopsInfo{" +
                "shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopPhone='" + shopPhone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shopName);
        dest.writeString(shopAddress);
        dest.writeString(shopPhone);
    }
}
