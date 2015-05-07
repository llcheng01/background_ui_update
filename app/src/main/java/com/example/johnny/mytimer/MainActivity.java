package com.example.johnny.mytimer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    int i = 0;
    TextView tv;
    final Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.txtView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                UpdateGUI();
            }
        }, 0, 1000);

    }

    private void UpdateGUI() {
        i++;
        myHandler.post(myRunnable);
    }

    final Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            tv.setText(String.valueOf(i));
        }
    };
}
