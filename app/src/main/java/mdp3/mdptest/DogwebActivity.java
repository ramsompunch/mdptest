package mdp3.mdptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by JBTS-02-05 on 2017-10-30.
 */

public class DogwebActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dogweb);


        WebView WebView01 = (WebView) findViewById(R.id.webaaa);
        WebView01.setWebViewClient(new WebViewClient());
        WebSettings webSettings = WebView01.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebView01.loadUrl("http://192.168.0.100/project/happy-puppy/streaming.html");
    }
}
