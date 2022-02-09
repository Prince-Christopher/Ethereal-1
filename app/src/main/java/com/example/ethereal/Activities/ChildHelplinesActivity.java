package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ethereal.R;

public class ChildHelplinesActivity extends AppCompatActivity {

    private WebView childhelplinewv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_helplines);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
    }
        childhelplinewv = findViewById(R.id.childhelplinewv);
        childhelplinewv.setWebViewClient(new WebViewClient());
        childhelplinewv.loadUrl("https://www.childhelplineinternational.org/child-helplines/child-helpline-network/");
        WebSettings webSettings = childhelplinewv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        childhelplinewv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                final Uri uri = request.getUrl();
                if (uri.toString().startsWith("tel:")) {
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                } else if (uri.toString().startsWith("mailto:")) {
                    startActivity(new Intent(Intent.ACTION_SENDTO, uri));
                }
                else {
                    view.loadUrl(uri.toString());
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (childhelplinewv.canGoBack()){
            childhelplinewv.goBack();
        }
        else {
            super.onBackPressed();
        }

    }

}