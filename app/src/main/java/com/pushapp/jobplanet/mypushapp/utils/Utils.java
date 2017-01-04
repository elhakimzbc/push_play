package com.pushapp.jobplanet.mypushapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jobplanet on 1/4/17.
 */

public class Utils {

    /**
     * check network availability
     * @param ctx
     * @return
     */
    public static boolean isNetworkAvailable( Context ctx ){
        boolean connected = false;
        Log.d("DEBUG","Connection "+connected);
        ConnectivityManager connMan = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = connMan.getAllNetworkInfo();

        for (NetworkInfo anInfo : info) {
            if (anInfo.getState() == NetworkInfo.State.CONNECTED ||
                    anInfo.getState()== NetworkInfo.State.CONNECTING ) {
                Toast.makeText(ctx, "Internet is connected", Toast.LENGTH_SHORT).show();
                connected = true;
                Log.d("DEBUG","Connection "+connected);

            }
        }

        return connected;
    }


    /**
     * @obsolete , use volley's methods
     * check server service availability
     * @param urlStr
     * @return
     */
    public static InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return in;
    }

}
