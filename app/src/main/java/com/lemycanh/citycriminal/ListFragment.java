package com.lemycanh.citycriminal;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mLvProblems;

    public ListFragment() {
        // Required empty public constructor
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnProblemChangedEvent(ProblemChangedEvent event) {
        mLvProblems.invalidateViews();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mLvProblems = view.findViewById(R.id.lv_problems);
        mLvProblems.setAdapter(new ProblemAdapter(getActivity()));
        mLvProblems.setOnItemClickListener(this);
        return view;
    }

    public static Fragment CreateFragment() {
        return new ListFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Problem problem = (Problem) mLvProblems.getAdapter().getItem(position);
        EventBus.getDefault().post(new ListChangedEvent(problem));
    }

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
}
