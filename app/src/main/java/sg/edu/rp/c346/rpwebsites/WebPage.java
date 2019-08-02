package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPage extends AppCompatActivity {

    WebView wvWebPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);

        wvWebPage = findViewById(R.id.webViewWebpage);
        wvWebPage.setWebViewClient(new WebViewClient());

        Intent intentReceived = getIntent();

        String url = intentReceived.getStringExtra("url");
        wvWebPage.loadUrl(url);

    }
}
