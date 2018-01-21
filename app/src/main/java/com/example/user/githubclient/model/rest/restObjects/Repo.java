package com.example.user.githubclient.model.rest.restObjects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 20.01.2018.
 */

public class Repo implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("description")
    private String description;
    @SerializedName("forks_count")
    private int forks_count;
    @SerializedName("watchers")
    private int watchers;

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeValue(owner);
        parcel.writeString(description);
        parcel.writeInt(forks_count);
        parcel.writeInt(watchers);
    }

    public static final Parcelable.Creator<Repo> CREATOR = new Parcelable.Creator<Repo>() {

        @Override
        public Repo createFromParcel(Parcel parcel) {
            return new Repo(parcel);
        }

        @Override
        public Repo[] newArray(int i) {
            return new Repo[i];
        }
    };

    private Repo(Parcel parcel) {
        name = parcel.readString();
        owner = (Owner) parcel.readValue(Owner.class.getClassLoader());
        description = parcel.readString();
        forks_count = parcel.readInt();
        watchers = parcel.readInt();
    }
}
