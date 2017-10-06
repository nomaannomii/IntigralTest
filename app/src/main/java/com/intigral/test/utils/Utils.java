package com.intigral.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class Utils {


    private static Utils appUtils = null;
    private final Context context;

    public Utils(Context context) {
        this.context = context;
    }


    public static Utils getAppUtils(Context context) {
        if (appUtils == null) ;
        appUtils = new Utils(context);
        return appUtils;
    }

    public void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public boolean isOnline(Context ctx) {
        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivity.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivity.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }
}
