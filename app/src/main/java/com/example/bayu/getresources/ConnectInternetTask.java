package com.example.bayu.getresources;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bayu on 17/10/17.
 */

public class ConnectInternetTask extends AsyncTask<String,Void,String>{

    Context ctx;
    ConnectInternetTask(Context ct){
        ctx=ct;
    }
    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;
        try {
            URL myUrl = new URL(s1);
            HttpURLConnection myCOn = (HttpURLConnection)myUrl.openConnection();
            myCOn.setReadTimeout(10000);
            myCOn.setConnectTimeout(20000);
            myCOn.setRequestMethod("GET");
            myCOn.connect();

            in = myCOn.getInputStream();
            BufferedReader myBuff = new BufferedReader(new InputStreamReader(in));
            StringBuilder st = new StringBuilder();
            String line = "";

            while ((line = myBuff.readLine())!=null){
                st.append(line+" \n");
            }
            myBuff.close();
            in.close();
            return st.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.myText.setText(s);
    }
}

