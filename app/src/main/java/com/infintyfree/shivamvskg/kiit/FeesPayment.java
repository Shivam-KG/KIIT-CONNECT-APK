package com.infintyfree.shivamvskg.kiit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class FeesPayment extends AppCompatActivity {

    private WebView feesweb;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_payment);

        feesweb = findViewById(R.id.feeswebview);

        // Enable JavaScript (optional)
        WebSettings webSettings = feesweb.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set a WebViewClient to handle page navigation and load URLs within the WebView
        feesweb.setWebViewClient(new WebViewClient());

        // Load a web page
        feesweb.loadUrl("https://form.kiit.ac.in/payment/");

    }

    // Handle the Back button press to navigate back within the WebView
    @Override
    public void onBackPressed() {
        if (feesweb.canGoBack()) {
            feesweb.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
