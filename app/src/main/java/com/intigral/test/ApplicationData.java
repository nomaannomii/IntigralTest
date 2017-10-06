package com.intigral.test;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.intigral.test.retrofit.RestClient;

public class ApplicationData extends Application {

    private static ApplicationData mInstance;
    public static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ApplicationData getInstance() {
        return mInstance;
    }

    public static RestClient getRestClient() {
        restClient = new RestClient();
        return restClient;
    }

    public static void setFragment(Fragment fragment, FragmentManager fragmentManager, int container) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

