package com.heemadev.alarm;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

public class RingtonePreference extends PreferenceFragment {
    Preference preference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ringtone_content);
        preference = this.getPreferenceScreen().getPreference(0);
        preference.setEnabled(true);
//        preference.setSummary("choice your ringtone");
    }

    public void set_enabled(boolean enabled) {
        preference.setEnabled(enabled);
    }

    public void set_summary(String s) {
        preference.setSummary(s);
    }


}
