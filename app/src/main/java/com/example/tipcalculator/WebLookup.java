package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebLookup extends AppCompatActivity {
public WebView appWebView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Here I set up the WebView using the WebView I made in the xml.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_lookup);
        appWebView = (WebView) findViewById(R.id.webID);
        // I use this line to ensure clicking links keeps the user in the widget rather than opening the browser.
        appWebView.setWebViewClient(new WebViewClient());
    }
    public void webButton(View v){
        // Here I make it so when one presses the button the WebView loads a link with the string from the EditText.
        EditText urlString = findViewById(R.id.url);
        appWebView.loadUrl(urlString.getText().toString());
    }
    @Override
    // I use this part to check for when the back button is pressed to make the WebView go back to the previous page.
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && appWebView.canGoBack()) {
            appWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}