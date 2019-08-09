package com.tarlochan.c0751142_mad3125_midterm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {

    @BindView(R.id.idWebView)
    WebView idWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String link = getIntent().getStringExtra("link");
        Toast.makeText(this, link, Toast.LENGTH_SHORT).show();

        idWebView.getSettings().setJavaScriptEnabled(true);
        idWebView.getSettings().setLoadsImagesAutomatically(true);
        idWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        idWebView.setWebViewClient(new WebViewClient());
        idWebView.loadUrl(link);
        WebSettings webSettings = idWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

}
