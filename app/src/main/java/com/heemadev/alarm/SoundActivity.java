package com.heemadev.alarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SoundActivity extends AppCompatActivity {
    public static final String pathKey = "path", activeKey = "active";
    private static boolean flag_onPause = false;
    TextView tv_back_action;
    FrameLayout ringtoneContainer;
    Switch switchSound;
    private String pathOfRingtone;
    private boolean active = true;
    private RingtonePreference RTP;

    public static String fromPathToTitle(Context c, String path) {
        return RingtoneManager.getRingtone(c, Uri.parse(path)).getTitle(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        RTP = new RingtonePreference();
        getFragmentManager().beginTransaction().replace(R.id.ringtoneContainer, RTP).commit();

        tv_back_action = findViewById(R.id.backArrow);
        ringtoneContainer = findViewById(R.id.ringtoneContainer);


        switchSound = findViewById(R.id.switchAlarmSound);

        tv_back_action.setOnClickListener(view -> {
            if (!active) {
                active = switchSound.isChecked();
            }
            Intent i = new Intent();
            i.putExtra(pathKey, pathOfRingtone);
            i.putExtra(activeKey, active);
            setResult(RESULT_OK, i);
            finish();
        });

        switchSound.setOnClickListener(view -> {
            Log.e("onCreate: ", String.valueOf(switchSound.isChecked()));
            active = switchSound.isChecked();
            if (active) {
                switchSound.setText("On");
                RTP.set_enabled(true);
            } else {
                switchSound.setText("Off");
                RTP.set_enabled(false);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag_onPause = true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        pathOfRingtone = sharedPreferences.getString("ringtone", "");
        String title = fromPathToTitle(this, pathOfRingtone);
        RTP.set_summary(title);
        flag_onPause = false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent();
        i.putExtra(pathKey, pathOfRingtone);
        i.putExtra(activeKey, active);
        setResult(RESULT_OK, i);
        finish();
    }
}