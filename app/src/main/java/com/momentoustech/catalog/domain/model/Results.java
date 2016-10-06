package com.momentoustech.catalog.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kleyvert on 10/4/16.
 */
public class Results implements Parcelable, Comparable<Results> {

    public String id;

    public String title;

    public String thumbnail;

    public String condition;

    public double price;

    public double available_quantity;

    // Constructor
    public Results(String title, String thumbnail, String condition, double price, double count) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.condition = condition;
        this.price = price;
        this.available_quantity = count;
    }

    /**
     * Compare between the object for determinate the order
     *
     * @param c {@link Results}
     * @return
     */
    @Override
    public int compareTo(Results c) {
        return title.substring(0, 1).compareToIgnoreCase(c.title.substring(0, 1));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(thumbnail);
        dest.writeString(condition);
        dest.writeDouble(price);
        dest.writeDouble(available_quantity);

    }

    protected Results(Parcel in) {
        id = in.readString();
        title = in.readString();
        thumbnail = in.readString();
        condition = in.readString();
        price = in.readDouble();
        available_quantity = in.readDouble();

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}
