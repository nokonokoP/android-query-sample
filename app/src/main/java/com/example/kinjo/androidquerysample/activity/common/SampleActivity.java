package com.example.kinjo.androidquerysample.activity.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kinjo.androidquerysample.common.AQuery;

/**
 * サンプルアプリのベースActivity
 * Created by kinjo on 2015/07/10.
 */
public class SampleActivity extends AppCompatActivity {

    protected AQuery q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.q = new AQuery(this);
    }

}
