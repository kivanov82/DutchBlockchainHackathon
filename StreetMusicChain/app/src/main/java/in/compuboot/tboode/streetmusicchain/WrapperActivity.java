package in.compuboot.tboode.streetmusicchain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WrapperActivity extends AppCompatActivity {
    WebView wrapperView;
    WebSettings wrapperSettings;
    UriManager uriManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper);

        wrapperView = (WebView) findViewById(R.id.WrapperView);

        setWebviewSettings();
        addJavascriptInterfaces();

        uriManager = UriManager.getInstance();

        wrapperView.loadUrl(uriManager.getUri());
        wrapperView.setWebViewClient(new MyWebViewClient());
    }

    private void setWebviewSettings()
    {
        wrapperSettings = wrapperView.getSettings();

        wrapperSettings.setJavaScriptEnabled(true);
        wrapperSettings.setDomStorageEnabled(true);
        wrapperSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        wrapperSettings.setBuiltInZoomControls(false);
        wrapperSettings.setAllowFileAccess(true);
        wrapperSettings.setSupportZoom(false);
        wrapperSettings.setPluginState(WebSettings.PluginState.ON);
        wrapperSettings.setAllowFileAccess(true);
    }

    private void addJavascriptInterfaces()
    {
        wrapperView.addJavascriptInterface(new WebAppInterface(this), "android");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

