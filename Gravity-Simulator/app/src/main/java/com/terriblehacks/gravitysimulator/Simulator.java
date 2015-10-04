package com.terriblehacks.gravitysimulator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Malzberry on 10/4/2015.
 */
public class Simulator extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simulator);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
