package com.example.bawei.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bawei.baselibrary.common.StartIntent;
import com.example.bawei.usermodule.view.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartIntent.getInstance().start(this, LoginActivity.class);
        this.finish();
    }
}
