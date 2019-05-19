package com.hidayat.zulfa.uas.Splash;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hidayat.zulfa.uas.R;

public class SplashActivity extends Activity {

    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        textView = (TextView)findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3);

        ProgressAnimation();
    }

    private void ProgressAnimation() {
        ProgressBar_Anim anim = new ProgressBar_Anim(this, progressBar, textView, 0f, 100f);
        anim.setDuration(3000);
        progressBar.setAnimation(anim);
    }
}
