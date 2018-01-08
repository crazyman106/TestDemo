package com.example.administrator.gmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

public class MapsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Log.e("main_activity", ((TextView) findViewById(R.id.txt)).getText().toString());
//        startActivity(new Intent(this,ScrollerDemo.class));
        startActivity(new Intent(this,VideoDemo.class));
    }

}
