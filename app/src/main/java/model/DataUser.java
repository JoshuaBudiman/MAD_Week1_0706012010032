package model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataUser implements Parcelable {
    private String Name, Address;
    private int Age;

    public DataUser(String name, int age, String address) {
        Name = name;
        Age = age;
        Address = address;
    }

    protected DataUser(Parcel in) {
        Name = in.readString();
        Address = in.readString();
        Age = in.readInt();
    }

    public static final Creator<DataUser> CREATOR = new Creator<DataUser>() {
        @Override
        public DataUser createFromParcel(Parcel in) {
            return new DataUser(in);
        }

        @Override
        public DataUser[] newArray(int size) {
            return new DataUser[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Address);
        dest.writeInt(Age);
    }
}
