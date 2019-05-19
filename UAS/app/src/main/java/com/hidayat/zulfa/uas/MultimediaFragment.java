package com.hidayat.zulfa.uas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hidayat.zulfa.uas.Multimedia.OflineActivity;
import com.hidayat.zulfa.uas.Multimedia.OnlineActivity;

public class MultimediaFragment extends Fragment {

    Button btn1, btn2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multimedia,container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn1 = (Button)view.findViewById(R.id.btnofline);
        btn2 = (Button)view.findViewById(R.id.btnonline);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getContext(), OflineActivity.class);
                startActivity(a);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getContext(), OnlineActivity.class);
                startActivity(b);
            }
        });
    }

}
