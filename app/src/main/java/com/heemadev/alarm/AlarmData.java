package com.heemadev.alarm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.Collections;


@RequiresApi(api = Build.VERSION_CODES.N)
public class AlarmData extends AppCompatActivity {
    public static final int intSoundCode = 0, intVibrationCode = 1, intSnoozeCode = 2;
    final int[] intArrayWeakDay = {0, 0, 0, 0, 0, 0, 0};
    final Calendar cldr = Calendar.getInstance();
    TextView TVSat, TVSun, TVMon, TVTus, TVWin, TVTha, TVFri, TVChoiceWeakDays, TVSoundChoice, TVVibrationChoice, TVSnoozeChoice;
    EditText ETAlarmName;
    RelativeLayout soundLayout, vibrationLayout, snoozeLayout;
    Button btnCancel, btnSave;
    ImageButton imgCalender;
    SwitchCompat switchOfSound, switchOfVibration, switchOfSnooze;
    String strArrayRepeatDays[] = {"sat", "sun", "mon", "tus", "win", "tha", "fri"};

    int[] intArrayWeakColor;
    ArrayList<String> arraylistDays = new ArrayList<String>();
    Intent intent;
    TimePicker timePicker;
    DatePickerDialog picker;
    int intDay = cldr.get(Calendar.DAY_OF_MONTH), intMonth = cldr.get(Calendar.MONTH) + 1, intYear = cldr.get(Calendar.YEAR);

    /* var to save in data base */
    //from this activity
    private String strAlarmName;
    private String strChoiceDays;
    //from sound activity
    private String strPathOfRingtone;
    private boolean boolActiveOfRingtone;
    // from vibration activity
    private String strSelectedVibration;
    private boolean boolActiveOfVibration;


    // from snooze activity
    private String strSnoozeInterval,strSnoozeRepeat;
    private boolean boolActiveOfSnooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_data);
        Collections.addAll(arraylistDays, new String[]{"", "", "", "", "", "", ""});

        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);


        TVSat = findViewById(R.id.TVSat);
        TVSun = findViewById(R.id.TVSun);
        TVMon = findViewById(R.id.TVMon);
        TVTus = findViewById(R.id.TVTus);
        TVWin = findViewById(R.id.TVWin);
        TVTha = findViewById(R.id.TVTha);
        TVFri = findViewById(R.id.TVFri);
        TVChoiceWeakDays = findViewById(R.id.TVChoiceWeakDays);
        TVSoundChoice = findViewById(R.id.TVSoundChoice);
        TVVibrationChoice = findViewById(R.id.TVVibrationChoice);
        TVSnoozeChoice = findViewById(R.id.TVSnoozeChoice);

        ETAlarmName = findViewById(R.id.ETAlarmName);

        soundLayout = findViewById(R.id.soundLayout);
        vibrationLayout = findViewById(R.id.vibrationLayout);
        snoozeLayout = findViewById(R.id.SnoozeLayout);

        switchOfSound = findViewById(R.id.switchOfSound);
        switchOfVibration = findViewById(R.id.switchOfVibration);
        switchOfSnooze = findViewById(R.id.switchOfSnooze);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        imgCalender = findViewById(R.id.calendarIcon);

        timePicker = findViewById(R.id.timeSelector);
//        try {
//            /****************************************************************************يتبع */
//            NumberPicker hoursPicker = (NumberPicker) timePicker.findViewById(Resources.getSystem().getIdentifier("hours", "id", "android"));
//            NumberPicker minutePicker = (NumberPicker) timePicker.findViewById(Resources.getSystem().getIdentifier("minute", "id", "android"));
//            Log.e("TAG", "onCreate:0");
//            NumberPicker separatorPicker = (NumberPicker) timePicker.findViewById(Resources.getSystem().getIdentifier("separator", "id", "android"));
//            Log.e("TAG", "onCreate:1");
//            hoursPicker.setBackgroundColor(Color.RED);
//            Log.e("TAG", "onCreate:2");
//            minutePicker.setBackgroundColor(Color.GREEN);
//            Log.e("TAG", "onCreate:3");
//            separatorPicker.setBackgroundColor(Color.BLACK);
//
//        } catch (Exception e) {
//            Log.e("TAG", "onCreate:eeeeeeeeeeeeeeeeeeee " );
//        }
        timePicker.setOnTimeChangedListener((timePicker1, i, i1) -> {

        });
//        timePicker.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                    Log.e("safgpkedg", "onTouch: o   = "+motionEvent.getAction() );
//                if (motionEvent.getAction()==MotionEvent.ACTION_BUTTON_PRESS){
//                    Log.e("safgpkedg", "onTouch: p" );
//                    view.setBackgroundColor(Color.RED);
//                }else if (motionEvent.getAction()==MotionEvent.ACTION_BUTTON_RELEASE){
//                    view.setBackgroundColor(Color.GREEN);
//                    Log.e("safgpkedg", "onTouch: r" );
//                }
//                return false;
//            }
//        });
        intArrayWeakColor = new int[]{TVSat.getCurrentTextColor(), TVSun.getCurrentTextColor(), TVMon.getCurrentTextColor(), TVTus.getCurrentTextColor(), TVWin.getCurrentTextColor(), TVTha.getCurrentTextColor(), TVFri.getCurrentTextColor()};

        TVChoiceWeakDays.setText(intDay + "/" + (intMonth + 1) + "/" + intYear);

        imgCalender.setOnClickListener(v -> {

            picker = new DatePickerDialog(AlarmData.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    TVChoiceWeakDays.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, intYear, intMonth, intDay);
            picker.show();
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
        btnSave.setOnClickListener(v -> {

        });
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TVSoundChoice.setText(SoundActivity.fromPathToTitle(this, sharedPreferences.getString("ringtone", "")));
        soundLayout.setOnClickListener(v -> {
            soundLayout.setAnimation(slideUp);
            intent = new Intent(this, SoundActivity.class);
            startActivityForResult(intent, intSoundCode);
        });
        vibrationLayout.setOnClickListener(v -> {
            vibrationLayout.setAnimation(slideUp);
            intent = new Intent(this, VibrationActivity.class);
            startActivityForResult(intent, intVibrationCode);
        });
        snoozeLayout.setOnClickListener(v -> {
            snoozeLayout.setAnimation(slideUp);
            intent = new Intent(this, SnoozeActivity.class);
            startActivityForResult(intent, intSnoozeCode);
        });
        TVSat.setOnClickListener(v -> {
            actionTextWeak((TextView) v, 0);
        });
        TVSun.setOnClickListener(v -> {
            actionTextWeak((TextView) v, 1);
        });
        TVMon.setOnClickListener(v -> {
            actionTextWeak((TextView) v, 2);
        });
        TVTus.setOnClickListener(v -> {
            actionTextWeak((TextView) v, 3);
        });
        TVWin.setOnClickListener(v -> {
            actionTextWeak((TextView) v, 4);
        });
        TVTha.setOnClickListener(v -> {
            actionTextWeak((TextView) v, 5);
        });
        TVFri.setOnClickListener(v -> {
            actionTextWeak((TextView) v, 6);
        });

    }

    private void actionTextWeak(TextView tv, int index) {

        if (intArrayWeakDay[index] == 1) {
            tv.setBackground(null);
            tv.setTextColor(intArrayWeakColor[index]);
            intArrayWeakDay[index] = 0;
            arraylistDays.set(index, "");
//            choiceWeakDays.setText(String.format("%s%s%s%s%s%s%s%s","Every ", days.get(0), days.get(1), days.get(2), days.get(3), days.get(4), days.get(5), days.get(6)));
            TVChoiceWeakDays.setText(String.format("%s%s", "Every ", fromArrayListToString(arraylistDays)));
            if (!contain(intArrayWeakDay, 1)) {
                TVChoiceWeakDays.setText(String.valueOf(intDay) + String.valueOf("/") + String.valueOf((intMonth + 1)) + String.valueOf("/") + String.valueOf(intYear));
            }
        } else {
            tv.setBackground(getResources().getDrawable(R.drawable.roundedtextchecked));
            tv.setTextColor(getResources().getColor(R.color.text_color));
            intArrayWeakDay[index] = 1;
            arraylistDays.set(index, strArrayRepeatDays[index]);
            TVChoiceWeakDays.setText(String.format("%s%s", "Every ", fromArrayListToString(arraylistDays)));
            if (!contain(intArrayWeakDay, 0)) {
                TVChoiceWeakDays.setText("Every Day");
            }
        }
    }

    private boolean contain(int[] a, int value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) return true;
        }
        return false;
    }

    private String fromArrayListToString(ArrayList<String> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            if (!arr.get(i).equals("") && str.equals("")) {
                str += ("" + arr.get(i));
            } else if (!arr.get(i).equals("")) {
                str += ("," + arr.get(i));
            }
        }
        return str;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == intSoundCode) {
            if (resultCode == RESULT_OK) {
                strPathOfRingtone = data.getStringExtra(SoundActivity.pathKey);
                boolActiveOfRingtone = data.getBooleanExtra(SoundActivity.activeKey, false);
                Log.e("TAG", "pathOfRingtone: " + strPathOfRingtone + " active: " + boolActiveOfRingtone);
                switchOfSound.setChecked(boolActiveOfRingtone);
                TVSoundChoice.setText(SoundActivity.fromPathToTitle(this, strPathOfRingtone));
            }
        }
        if (requestCode == intVibrationCode) {
            if (resultCode == RESULT_OK) {
                strSelectedVibration = data.getStringExtra(VibrationActivity.selectedKey);
                boolActiveOfVibration = data.getBooleanExtra(VibrationActivity.activeKey, false);
                Log.e("TAG", "strSelectedVibration : " + strSelectedVibration + " active vibration: " + boolActiveOfVibration);
                switchOfVibration.setChecked(boolActiveOfVibration);
                TVVibrationChoice.setText(strSelectedVibration);
            }
        }
        if (requestCode == intSnoozeCode) {
            if (resultCode == RESULT_OK) {
                strSnoozeInterval = data.getStringExtra(SnoozeActivity.intervalKey);
                strSnoozeRepeat = data.getStringExtra(SnoozeActivity.repeatKey);
                boolActiveOfSnooze = data.getBooleanExtra(SnoozeActivity.activeKey,false);
                Log.e("TAG", "strSnoozeInterval : " + strSnoozeInterval + " strSnoozeRepeat: " + strSnoozeRepeat);
                switchOfSnooze.setChecked(boolActiveOfVibration);
                TVSnoozeChoice.setText(String.format("%s,%s", strSnoozeInterval, strSnoozeRepeat));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}