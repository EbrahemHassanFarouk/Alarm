package com.heemadev.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VibrationActivity extends AppCompatActivity {
    public static final String activeKey="active",selectedKey="select";
    TextView tv_back_action;
    Switch switchVibration;

    ListView LVVibration;
    AdapterOfListview adapterOfListview;
    String data[] = new String[]{"Short", "Medium", "Basic call", "Heartbeat", "Ticktock", "Waltz", "Zig-zig-zig", "Silent"};
    boolean active;
    String selected="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration);

        tv_back_action = findViewById(R.id.backArrow);

        switchVibration = findViewById(R.id.switchVibration);

        LVVibration = findViewById(R.id.LVVibration);

        adapterOfListview = new AdapterOfListview(this, data);
        LVVibration.setAdapter(adapterOfListview);

        tv_back_action.setOnClickListener(view -> {
            selected= adapterOfListview.selected;
            if (!active){
                active=switchVibration.isChecked();
            }
            Intent i = new Intent();
            i.putExtra(activeKey,active);
            i.putExtra(selectedKey,selected);
            setResult(RESULT_OK, i);
            finish();
        });

        switchVibration.setOnClickListener(view -> {
            Log.e("onCreate: switchVibration : ", String.valueOf(switchVibration.isChecked()));
            active = switchVibration.isChecked();
            if (active) {
                switchVibration.setText("On");
                LVVibration.setActivated(true);

            } else {
                switchVibration.setText("Off");
                LVVibration.setActivated(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selected= adapterOfListview.selected;
        if (!active){
            active=switchVibration.isChecked();
        }
        Intent i = new Intent();
        i.putExtra(activeKey,active);
        i.putExtra(selectedKey,selected);
        setResult(RESULT_OK, i);
        finish();
    }
}