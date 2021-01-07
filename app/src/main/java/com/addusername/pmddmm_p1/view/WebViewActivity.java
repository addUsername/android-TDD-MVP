package com.addusername.pmddmm_p1.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.addusername.pmddmm_p1.R;
import com.addusername.pmddmm_p1.interfaces.RequiredViewOps;
import com.addusername.pmddmm_p1.interfaces.RequiredWebViewOps;

public class WebViewActivity extends Activity implements RequiredWebViewOps {

    private WebView wb;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        //url parameters passed from mainActivity
        url = getIntent().getStringExtra("URL");
        setupWebview(getIntent().getBooleanExtra("shouldUseChrome",false));
    }

    private void setupWebview(boolean shouldUseChrome) {
        wb = (WebView) findViewById(R.id.webview);
        if(shouldUseChrome){
            wb.setWebChromeClient(new WebChromeClient());
            wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            loadUrl(url);
        }else{
            wb.setWebViewClient(new WebViewClient());
            wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
            wb.getSettings().setJavaScriptEnabled(true);
            wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            loadUrl(url);
        }
    }
    @Override
    public void loadUrl(String url) {
        wb.loadUrl(url);
    }
    private class WebViewClient extends android.webkit.WebViewClient{
    }
}
