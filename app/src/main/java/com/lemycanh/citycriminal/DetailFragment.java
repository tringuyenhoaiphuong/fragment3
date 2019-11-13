package com.lemycanh.citycriminal;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvTimestamp;
    private CheckBox mCkResolved;
    private Problem mProblem;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ListChangedEvent event) {
        //mTvDetail.setText(event.getMessage());
        mProblem = event.getProblem();
        mTvTitle.setText(mProblem.getTitle());
        mTvContent.setText(mProblem.getContent());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        mTvTimestamp.setText(dateFormat.format(mProblem.getTimestamp()));
        mCkResolved.setChecked(mProblem.isResolved());
        mCkResolved.setOnCheckedChangeListener(this);
    };

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        this.mTvTitle = view.findViewById(R.id.tv_problem_title);
        this.mTvContent = view.findViewById(R.id.tv_problem_content);
        this.mTvTimestamp = view.findViewById(R.id.tv_problem_timestamp);
        this.mCkResolved = view.findViewById(R.id.tv_problem_resolved);
        return view;
    }

    public static Fragment CreateFragment() {
        return new DetailFragment();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mProblem.setResolved(isChecked);
        EventBus.getDefault().post(new ProblemChangedEvent());
    }
}
