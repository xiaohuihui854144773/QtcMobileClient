package com.example.dh.qtcmobileclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh.qtcmobileclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_msg, container, false);
        return layout;
    }

}
