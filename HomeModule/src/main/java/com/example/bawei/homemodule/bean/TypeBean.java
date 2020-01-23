package com.example.bawei.homemodule.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 15:39
 * @Email 1151403054@qq.com
 */
public class TypeBean implements Parcelable {

    /**
     * id : 1
     * typename : 游戏
     */

    private int id;
    private String typename;

    protected TypeBean(Parcel in) {
        id = in.readInt();
        typename = in.readString();
    }

    public static final Creator<TypeBean> CREATOR = new Creator<TypeBean>() {
        @Override
        public TypeBean createFromParcel(Parcel in) {
            return new TypeBean(in);
        }

        @Override
        public TypeBean[] newArray(int size) {
            return new TypeBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(typename);
    }
}
