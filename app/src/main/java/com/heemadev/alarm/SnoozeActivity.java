package com.heemadev.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SnoozeActivity extends AppCompatActivity {
    public static final String intervalKey="interval",repeatKey="repeat",activeKey="active";

    TextView tv_back_action;
    Switch switchSnooze;

    ListView LVInterval,LVRepeat;
    AdapterOfListview adapterOfListviewInterval ,adapterOfListviewRepeat;
    String dataOfInterval[] = new String[]{"5 minutes", "10 minutes", "15 minutes", "30 minutes"};
    String dataOfRepeat[] = new String[]{"3 times", "5 times", "Forever"};
    boolean active;
    String selectedInterval="",selectedRepeat="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooze);
        Intent intent=getIntent();
        selectedInterval=intent.getStringExtra(intervalKey);
        selectedRepeat=intent.getStringExtra(repeatKey);

        tv_back_action = findViewById(R.id.backArrow);

        switchSnooze = findViewById(R.id.switchSnooze);

        LVInterval = findViewById(R.id.LVInterval);
        LVRepeat = findViewById(R.id.LVRepeat);

        adapterOfListviewInterval = new AdapterOfListview(this, dataOfInterval);
        LVInterval.setAdapter(adapterOfListviewInterval);
        adapterOfListviewRepeat = new AdapterOfListview(this, dataOfRepeat);
        LVRepeat.setAdapter(adapterOfListviewRepeat);

        tv_back_action.setOnClickListener(view -> {
            selectedInterval= adapterOfListviewInterval.selected;
            selectedRepeat= adapterOfListviewRepeat.selected;
            if (!active){
                active=switchSnooze.isChecked();
            }
            Intent i = new Intent();
            i.putExtra(activeKey,active);
            i.putExtra(intervalKey,selectedInterval);
            i.putExtra(repeatKey,selectedRepeat);
            setResult(RESULT_OK, i);
            finish();
        });

        switchSnooze.setOnClickListener(view -> {
            Log.e("onCreate: switchVibration : ", String.valueOf(switchSnooze.isChecked()));
            active = switchSnooze.isChecked();
            if (active) {
                switchSnooze.setText("On");
                LVInterval.setActivated(true);
                LVRepeat.setActivated(true);

            } else {
                switchSnooze.setText("Off");
                LVInterval.setActivated(false);
                LVRepeat.setActivated(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}