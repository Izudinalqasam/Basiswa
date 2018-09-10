package com.example.asus.beasiswa;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelBeasiswa implements Parcelable {

    int id;
    String nama,level,destination;

    public ModelBeasiswa(){

    }

    protected ModelBeasiswa(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        level = in.readString();
        destination = in.readString();
    }

    public static final Creator<ModelBeasiswa> CREATOR = new Creator<ModelBeasiswa>() {
        @Override
        public ModelBeasiswa createFromParcel(Parcel in) {
            return new ModelBeasiswa(in);
        }

        @Override
        public ModelBeasiswa[] newArray(int size) {
            return new ModelBeasiswa[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nama);
        parcel.writeString(level);
        parcel.writeString(destination);
    }
}
