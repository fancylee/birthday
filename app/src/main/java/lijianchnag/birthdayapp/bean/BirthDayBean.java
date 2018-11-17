package lijianchnag.birthdayapp.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by lijianchang on 2018/11/17:09:58.
 * Des :
 */

public class BirthDayBean {

    public BirthDayBean(int mBigDrawableId,int mContentDrableIdl,String title){
        this.mBigDrawableId = mBigDrawableId;
        this.mContentDrableIdl = mContentDrableIdl;
        this.title = title;
    }

    @DrawableRes
    int mBigDrawableId;

    @DrawableRes
    int mContentDrableIdl;

    public String title;

    public int getmBigDrawableId() {
        return mBigDrawableId;
    }

    public void setmBigDrawableId(int mBigDrawableId) {
        this.mBigDrawableId = mBigDrawableId;
    }

    public int getmContentDrableIdl() {
        return mContentDrableIdl;
    }

    public void setmContentDrableIdl(int mContentDrableIdl) {
        this.mContentDrableIdl = mContentDrableIdl;
    }
}
