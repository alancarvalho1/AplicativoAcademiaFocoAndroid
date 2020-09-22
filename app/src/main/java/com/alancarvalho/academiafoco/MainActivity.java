package com.alancarvalho.academiafoco;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar  = findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.getSettings().setDomStorageEnabled(true);

        //url do página
        webView.loadUrl("http://academiafoco.oaksoftwares.com.br/");

        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.setWebViewClient(new WebViewClient());

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE); //PARA MOSTRAR O PROGRESS BAR
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
            }

        });


    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView webView = findViewById(R.id.webview);
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        WebView webView = findViewById(R.id.webview);
        if (webView != null)
            webView.destroy();
        super.onDestroy();
    }


}