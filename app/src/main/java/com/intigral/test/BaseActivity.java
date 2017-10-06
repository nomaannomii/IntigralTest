package com.intigral.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.intigral.test.utils.Utils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
    }

    public boolean isOnline() {
        return Utils.getAppUtils(this).isOnline(this);
    }

    protected abstract int setLayoutId();

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
