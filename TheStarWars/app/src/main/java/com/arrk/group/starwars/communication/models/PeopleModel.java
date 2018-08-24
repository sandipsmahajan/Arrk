package com.arrk.group.starwars.communication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author SANDY
 * <p>
 * People model to hold star wars characters data
 */
public class PeopleModel implements Parcelable {

    @Expose
    private String name;
    @Expose
    private String height;
    @Expose
    private String mass;
    @Expose
    private String created;

    private PeopleModel(Parcel in) {
        name = in.readString();
        height = in.readString();
        mass = in.readString();
        created = in.readString();
    }

    public static final Creator<PeopleModel> CREATOR = new Creator<PeopleModel>() {
        @Override
        public PeopleModel createFromParcel(Parcel in) {
            return new PeopleModel(in);
        }

        @Override
        public PeopleModel[] newArray(int size) {
            return new PeopleModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(height);
        parcel.writeString(mass);
        parcel.writeString(created);
    }
}
