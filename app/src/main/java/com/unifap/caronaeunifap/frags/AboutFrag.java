package com.unifap.caronaeunifap.frags;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.unifap.caronaeunifap.Constants;
import com.unifap.caronaeunifap.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutFrag extends Fragment {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public AboutFrag(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFF000000, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view,url);
                progressBar.setVisibility(View.GONE);
            }
        });
        //webView.loadUrl(Constants.CARONAE_URL_BASE + "sobre_mobile.html");
        webView.loadUrl("https://caronae.org/sobre_mobile.html");
        return view;
    }
}
