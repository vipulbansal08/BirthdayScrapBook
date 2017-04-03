package com.example.android.birthdayscrapbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by banvipul on 4/3/17.
 */
public class BirthdayListFragment extends Fragment {
    private RecyclerView mBirthdayListRecyclerView;
    private BirthDayAdapter mAdapter;

    @Override
    public void onResume() {
        super.onResume();
        createUI();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.birthday_list_fragment, container, false);
        mBirthdayListRecyclerView = (RecyclerView) view.findViewById(R.id.birthday_list_recycler_view);
        mBirthdayListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        createUI();
        return view;
    }

    void createUI() {
        BirthdayRepository birthdayRepository = BirthdayRepository.get(getActivity());
        List<Birthday> birthdays = birthdayRepository.getBirthday();
        if (mAdapter == null) {
            mAdapter = new BirthDayAdapter(birthdays);
            mBirthdayListRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BirthdayHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Birthday mBirthday;

        @Override
        public void onClick(View view) {
            Intent intent = BirthdayDetailPagerActivity.newIntent(getActivity(), mBirthday.getId());
            startActivity(intent);
        }

        public BirthdayHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_birthday_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_birthday_date_text_view);
            itemView.setOnClickListener(this);
        }

        public void bindBirthday(Birthday birthday) {
            mBirthday = birthday;
            mTitleTextView.setText(mBirthday.getTitle());
            mDateTextView.setText(mBirthday.getDate().toString());
        }


    }

    private class BirthDayAdapter extends RecyclerView.Adapter<BirthdayHolder> {
        private List<Birthday> mBirthday;

        BirthDayAdapter(List<Birthday> birthdays) {
            mBirthday = birthdays;
        }

        @Override
        public BirthdayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_birthday, parent, false);
            return new BirthdayHolder(view);
        }

        @Override
        public int getItemCount() {
            return mBirthday.size();
        }

        @Override
        public void onBindViewHolder(BirthdayHolder holder, int position) {
            Birthday birthday = mBirthday.get(position);
            holder.bindBirthday(birthday);
        }
    }
}
