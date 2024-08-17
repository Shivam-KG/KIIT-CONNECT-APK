package com.infintyfree.shivamvskg.kiit;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Simulate loading process with a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity
                Intent intent = new Intent(Loading.this, NewsActivity.class);
                startActivity(intent);

                // Finish the loading activity
                finish();
            }
        }, 3000); // Delay of 3 seconds (adjust as needed)

    }
}
