package com.example.ex11_2024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ex11_2024.Receivers.CustomHPReciever;
import com.example.ex11_2024.Receivers.HeadphonesReceiver;

public class MainActivity extends AppCompatActivity {
    TextView tvda, tvhc, tvthc;
    int temp;
    private static MainActivity ins;

    private HeadphonesReceiver hc;
    private CustomHPReciever tHPR;
    private final String THC_REC = "com.example.ex11_2024.Receivers.CustomHPReciever";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvda = findViewById(R.id.tvda);
        tvhc = findViewById(R.id.tvhc);
        tvthc = findViewById(R.id.tvthc);
        hc = new HeadphonesReceiver();
        tHPR = new CustomHPReciever();

        ins = this;

        SharedPreferences bootcounter = getSharedPreferences("bootcount", MODE_PRIVATE);
        temp = bootcounter.getInt("boot", -1);
        tvda.setText("Device Activations: " + temp);
        temp = bootcounter.getInt("hcon", 0);
        tvhc.setText("Headphones connections:  " + temp);


    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter hcf = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(hc, hcf);
        IntentFilter thcf = new IntentFilter(THC_REC);
        registerReceiver(tHPR, thcf);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(hc);
        unregisterReceiver(tHPR);
    }

    public void updateTheTextView_headphone(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                tvhc.setText(t);
            }
        });

    }
    public static MainActivity  getInstance(){
        return ins;
    }

    public void updateTheTextView_theadphone(String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                tvthc.setText(t);
            }
        });
    }
}