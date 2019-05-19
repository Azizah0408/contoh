package com.hidayat.zulfa.uas.Multimedia;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hidayat.zulfa.uas.R;

public class OnlineActivity extends Activity {

    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        w = (WebView)findViewById(R.id.webyoutube);
        w.getSettings().setJavaScriptEnabled(true);
        w.setWebViewClient(new WebViewClient());
        w.loadUrl("https://www.youtube.com/watch?v=PHvHxK_gfa0");
    }
}
