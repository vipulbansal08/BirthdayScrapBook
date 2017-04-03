package com.example.android.birthdayscrapbook;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by banvipul on 4/3/17.
 */
public class BirthdayRepository {
    private static BirthdayRepository sBirthdayRepository;
    private List<Birthday> mBirthday;

    public static BirthdayRepository get(Context context) {
        if (sBirthdayRepository == null) {
            sBirthdayRepository = new BirthdayRepository(context);
        }
        return sBirthdayRepository;
    }

    private BirthdayRepository(Context context) {
        mBirthday = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Birthday birthday = new Birthday();
            birthday.setTitle("BirthDay #" + i);
            mBirthday.add(birthday);
        }
    }

    public List<Birthday> getBirthday() {
        return mBirthday;
    }

    public Birthday getCrime(UUID id) {
        for (Birthday birthday : mBirthday) {
            if (birthday.getId().equals(id)) {
                return birthday;
            }
        }
        return null;
    }
}
