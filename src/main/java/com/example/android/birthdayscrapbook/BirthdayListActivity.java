package com.example.android.birthdayscrapbook;

import android.support.v4.app.Fragment;

/**
 * Created by banvipul on 4/3/17.
 */
public class BirthdayListActivity extends BirthdayFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BirthdayListFragment();
    }
}
