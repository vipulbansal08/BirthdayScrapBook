package com.example.android.birthdayscrapbook;

import java.util.Date;
import java.util.UUID;

/**
 * Created by banvipul on 4/3/17.
 */
public class Birthday {
    private UUID mId;
    private String mTitle;
    private Date mDate;

    public Birthday() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

}
