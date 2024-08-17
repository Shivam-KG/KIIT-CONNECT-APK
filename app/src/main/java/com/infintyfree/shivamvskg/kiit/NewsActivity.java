package com.infintyfree.shivamvskg.kiit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    private WebView newsweb;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsweb = findViewById(R.id.newswebview);

        // Enable JavaScript (optional)
        WebSettings webSettings = newsweb.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set a WebViewClient to handle page navigation and load URLs within the WebView
        newsweb.setWebViewClient(new WebViewClient());

        // Load a web page
        newsweb.loadUrl("https://news.kiit.ac.in/");

    }

    // Handle the Back button press to navigate back within the WebView
    @Override
    public void onBackPressed() {
        if (newsweb.canGoBack()) {
            newsweb.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
