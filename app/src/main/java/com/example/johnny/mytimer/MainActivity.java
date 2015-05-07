package com.example.johnny.mytimer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    int i = 0;
    TextView tv;
    final Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // Get the bundle and extract data by Key
            Bundle b = msg.getData();
            String key = b.getString("MyKey");
            tv.setText(tv.getText() + "Item: " + key + System.getProperty("line.separator"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.txtView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Create a new Thread
        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        Bundle b = new Bundle();
                        b.putString("MyKey", "My Value:" + String.valueOf(i));
                        msg.setData(b);
                        // Send message to the handler with the current message handler
                        myHandler.sendMessage(msg);

                    } catch (Exception e) {
                        Log.v("Error", e.toString());
                    }
                }
            }
        });

        background.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
