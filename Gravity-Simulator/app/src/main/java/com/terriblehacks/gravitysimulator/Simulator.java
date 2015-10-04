package com.terriblehacks.gravitysimulator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Malzberry on 10/4/2015.
 */
public class Simulator extends Activity {
    TextView instructions;
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

    public void onLift(){
        instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText("Let Go.");
    }

    public void onDrop(){
        instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText("Gravity Simulation Complete.");
    }

}
