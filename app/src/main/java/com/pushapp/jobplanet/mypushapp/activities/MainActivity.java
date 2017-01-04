package com.pushapp.jobplanet.mypushapp.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pushapp.jobplanet.mypushapp.R;
import com.pushapp.jobplanet.mypushapp.utils.Command;
import com.pushapp.jobplanet.mypushapp.utils.VolleyRequestor;

public class MainActivity extends AppCompatActivity {

    public static String URL_API1="http://www.google.co.id";
    ProgressDialog progressDialog;
    private static final String TAG = "MainActivity";
    private VolleyRequestor volleyRequestor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);

        volleyRequestor=VolleyRequestor.getInstance();
        volleyRequestor.setActivity(this);
        volleyRequestor.setProgressDialog(progressDialog);

        volleyRequestor.requestTime(URL_API1, 500, new Command() {
            @Override
            public void execute(Object info) {
                Log.d(MainActivity.class.getName(),"API 2 Picked");
                Toast.makeText(getApplicationContext(), "API 2 Picked in "+info+" ms", Toast.LENGTH_SHORT).show();
            }
        });


    }




}
