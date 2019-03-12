package com.age.uiautodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBeginClick(View view) throws IOException {
        //Toast.makeText(MainActivity.this, view.getId()+"", Toast.LENGTH_LONG).show();
        Process p = Runtime.getRuntime().exec("ls");
        String data = null;
        BufferedReader ie = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String error = null;
        while((error = ie.readLine()) != null
                && !error.equals("null")) {
            data += error + "\n";
        }
        String line = null;
        while((line = in.readLine()) != null
                && !line.equals("null")) {
            data += line + "\n";
        }

        Log.v("ls", data);
    }
}
