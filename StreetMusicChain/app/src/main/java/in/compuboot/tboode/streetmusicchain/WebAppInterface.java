package in.compuboot.tboode.streetmusicchain;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show the login error toast*/
    @JavascriptInterface
    public void showLoginError() {
        Toast.makeText(mContext, "Error: Your username or password is incorrect!", Toast.LENGTH_SHORT).show();
    }

    /**
     * show a message
     * @param msg the message to show
     */
    @JavascriptInterface
    public void toast(String msg)
    {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Start the QR scanner activity.
     */
    @JavascriptInterface
    public void scanQR()
    {
        Intent myIntent = new Intent(mContext, QRScannerActivity.class);
        mContext.startActivity(myIntent);
    }
}
