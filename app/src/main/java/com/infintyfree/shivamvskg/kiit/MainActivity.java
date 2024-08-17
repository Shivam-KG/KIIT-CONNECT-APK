package com.infintyfree.shivamvskg.kiit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;

    private AdView mAdView;

    FirebaseAnalytics analytics;
    FirebaseRemoteConfig remoteConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int currentVersionCode;
        currentVersionCode = getCurrentVersionCode();
        Log.d( "myApp", String.valueOf(currentVersionCode));


        remoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(5)
                .build();
        remoteConfig.setConfigSettingsAsync(configSettings);
        remoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if(task.isSuccessful()){
                    final String new_version_code = remoteConfig.getString("new_version_code");
                    if(Integer.parseInt(new_version_code) > getCurrentVersionCode()){
                        showUpdateDiaglog();
                    }
                }
            }
        });

        //MAIN ACTIVITY TO SAP PORTAL WEBVIEW
        cardView1 = findViewById(R.id.sap1);
        Button transferButton = cardView1.findViewById(R.id.transfer_button);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to specify the destination activity
                Intent intent = new Intent(MainActivity.this, sap.class);

                // Optionally, pass any extra data to the destination activity
                intent.putExtra("key", "value");

                // Start the destination activity
                startActivity(intent);
            }
        });

        //MAIN ACTIVITY TO NEWS WEBVIEW
        cardView2 = findViewById(R.id.newss);
        Button transferButton2 = cardView2.findViewById(R.id.transfer_news);
        transferButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to specify the destination activity
                Intent intent = new Intent(MainActivity.this, Loading.class);

                // Optionally, pass any extra data to the destination activity
                intent.putExtra("key", "value");

                // Start the destination activity
                startActivity(intent);
            }
        });

        //MAIN ACTIVITY TO fees WebView
        cardView3 = findViewById(R.id.feespayment);
        Button transferButton3 = cardView3.findViewById(R.id.transfer_fees);
        transferButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to specify the destination activity
                Intent intent = new Intent(MainActivity.this, FeesPayment.class);

                // Optionally, pass any extra data to the destination activity
                intent.putExtra("key", "value");

                // Start the destination activity
                startActivity(intent);
            }
        });

        //MAIN ACTIVITY TO About TEXT VIEW
        cardView4 = findViewById(R.id.about);
        Button transferButton4 = cardView4.findViewById(R.id.transfer_about);
        transferButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to specify the destination activity
                Intent intent = new Intent(MainActivity.this, About.class);

                // Optionally, pass any extra data to the destination activity
                intent.putExtra("key", "value");

                // Start the destination activity
                startActivity(intent);
            }
        });

        //Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
           @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        analytics=FirebaseAnalytics.getInstance(this);
    }

    private void showUpdateDiaglog() {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New Update Available")
                .setMessage("Update Now")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1qybmSLqldfV0ahqo_We4E8NFfjGoPf1p?usp=sharing")));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "something Went Wrong.Try Again Later!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }).show();
        dialog.setCancelable(false);
}
    private int getCurrentVersionCode(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        }catch (Exception e) {
            Log.d("myApp",e.getMessage());
        }

        return packageInfo.versionCode;
    }

}
