package com.example.android.birthdayscrapbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by banvipul on 4/3/17.
 */
public class BirthdayDetailPagerActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Birthday> mBirthdays;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, BirthdayDetailPagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday_detail_pager);
        mViewPager = (ViewPager) findViewById(R.id.detail_view_pager);

        mBirthdays = BirthdayRepository.get(this).getBirthday();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Birthday birthday = mBirthdays.get(position);
                return BirthdayDetailFragment.newInstance(birthday.getId());
            }
            @Override
            public int getCount() {
                return mBirthdays.size();
            }
        });
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        for (int i = 0; i < mBirthdays.size(); i++) {
            if (mBirthdays.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break; }
        }
    }
}
