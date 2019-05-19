package com.hidayat.zulfa.uas.Multimedia;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hidayat.zulfa.uas.R;

public class OflineActivity extends AppCompatActivity {

    VideoView videoView1 , videoView2;
    Button play1, play2;

    MediaController mediaController, mediaController1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofline);
        videoView1 = (VideoView)findViewById(R.id.VV1);
        videoView2 = (VideoView)findViewById(R.id.VV2);

        play1 = (Button)findViewById(R.id.btnplay1);
        play2 = (Button)findViewById(R.id.btnplay2);

        mediaController = new MediaController(this);
        mediaController1 = new MediaController(this);

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vidio = "android.resource://com.hidayat.zulfa.uas/"+R.raw.kuy;
                Uri uri = Uri.parse(vidio);
                videoView1.setVideoURI(uri);
                videoView1.setMediaController(mediaController);
                mediaController.setAnchorView(videoView1);
                videoView1.start();
            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kay));
                videoView2.setMediaController(mediaController1);
                mediaController1.setAnchorView(videoView2);
                videoView2.start();

            }
        });
    }
}
