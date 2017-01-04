package com.pushapp.jobplanet.mypushapp.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pushapp.jobplanet.mypushapp.AppSingleton;
import com.pushapp.jobplanet.mypushapp.R;
import com.pushapp.jobplanet.mypushapp.activities.MainActivity;

/**
 * Created by jobplanet on 1/4/17.
 */

public class VolleyRequestor {

    public static final String  STR_REQUEST_TAG = "com.jobplanet.reqstring";
    public static final String  JSON_REQUEST_TAG = "com.jobplanet.request.json";
    public static final String  JSONARRAY_REQUEST_TAG = "com.jobplanet.request.jsonarray";
    public static final String  IMAGE_REQUEST_TAG = "com.jobplanet.request.image";
    public static final String  CACHE_REQUEST_TAG = "com.jobplanet.request.cache";

    ProgressDialog progressDialog;
    Activity activity;
    String currentApiUrl="http://api1.jobplanet.co.kr";

    private static VolleyRequestor __instance;


    public static VolleyRequestor getInstance(){
        if(__instance==null){
            __instance = new VolleyRequestor();
        }
        return __instance;
    }

    private VolleyRequestor(){

    }


    public void requestString(String url){


        progressDialog.setMessage("Loading...");
        progressDialog.show();



    }

    public void requestJson(String url){


    }

    public void requestJsonArray(String url){


    }

    public void imageLoader(String url){


    }

    public void requestCache(String url){

    }

    public void invalidateCache(String url){

    }

    public void deleteCache(String url){

    }

    public void clearCache(String url){

    }


    /**
     * request and get time information, if exceed, run Command execWhenFail
     * @param url
     * @param expectMillis
     * @param execWhenFail
     */
    public void requestTime(String url, final int expectMillis , final Command execWhenFail){

        final int millis = (int)System.currentTimeMillis();

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int delta = (int)System.currentTimeMillis() - millis;
                Log.i(STR_REQUEST_TAG,"time = "+delta);
                if(delta > expectMillis){
                    execWhenFail.execute(Integer.toString(delta));
                }else {
                    Toast.makeText(activity, "API 1 Picked in "+delta+"ms", Toast.LENGTH_SHORT).show();
                }
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              Log.e(STR_REQUEST_TAG,error.getMessage());
                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(activity.getApplicationContext()).addToRequestQueue(strReq,STR_REQUEST_TAG);
    }


    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
