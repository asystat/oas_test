package com.example.sebastian.oastests;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.appnexus.oas.mobilesdk.IReceiveAd;
import com.appnexus.oas.mobilesdk.XAdView;
import com.appnexus.oas.mobilesdk.errorhandler.XAdException;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mView = (RelativeLayout)findViewById(R.id.daframe);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    XAdView xAdView;
    @Override
    protected void onResume() {
        super.onResume();

        //OAS
        xAdView = new XAdView(this, new IReceiveAd() {
            @Override
            public void xAdLoaded(View view) {
                Log.e("OAS", "Ad loaded");
                mView.addView(view);
            }

            @Override
            public boolean xAdShouldDisplay(View view, WebView mWebView, String s) {
                if(s!=null)
                    Log.e("OAS Message: ", s);
                /*if(mWebView != null){
                    mWebView.setFocusable(true);
                    mWebView.setFocusableInTouchMode(true);
                    mWebView.getSettings().setJavaScriptEnabled(true);
                    mWebView.getSettings().setDomStorageEnabled(true);
                    mWebView.getSettings().setDatabaseEnabled(true);
                    mWebView.getSettings().setAppCacheEnabled(true);
                }*/
                Log.e("OAS", "llegó?"+view.getWidth()+"x"+view.getHeight());
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
                Log.e("OAS", lp.width+"x"+lp.height);
                return true;
            }

            @Override
            public void xAdFailed(View view, XAdException e) {
                Log.e("OAS", "xAdFailed: " + e.getMessage());
                e.printStackTrace();
            }

        });
        //XAdSlotConfiguration xAdConfig = xAdView.getAdSlotConfiguration();
        xAdView.setBackgroundColor(Color.CYAN);
        int vWidth = getSizeInDP(320);
        int vHeight = getSizeInDP(50);
        RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(vWidth,vHeight);
        Log.e("OAS", "Setting dimensions to "+vWidth+"x"+vHeight);
        layoutparams.addRule(RelativeLayout.CENTER_IN_PARENT);
        xAdView.setLayoutParams(layoutparams);
        xAdView.loadAd("de.yapo.cl", "nga.cl/Listado/Region-Metropolitana/Autos-camionetas-y-4x4", "Middle3", "", "");
        Log.e("OAS", "Loading ad");

    }

    private int getSizeInDP(int pixelSize) {
        float scale = getResources().getDisplayMetrics().density;
        int sizeInDP = (int) (pixelSize * scale);
        return sizeInDP;
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
