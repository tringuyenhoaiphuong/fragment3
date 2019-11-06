package com.lemycanh.citycriminal;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    private EditText mEtMessage;
    private Button mBtSend;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        this.mEtMessage = view.findViewById(R.id.et_message_to_send);
        this.mBtSend = view.findViewById(R.id.bt_send);
        this.mBtSend.setOnClickListener(v -> {
            String message = ListFragment.this.mEtMessage.getText().toString();
            EventBus.getDefault().post(new ListChangedEvent(message));
        });
        return view;
    }

    public static Fragment CreateFragment() {
        return new ListFragment();
    }
}
