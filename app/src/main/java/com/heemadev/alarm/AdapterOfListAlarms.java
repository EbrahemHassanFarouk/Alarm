package com.heemadev.alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

public class AdapterOfListAlarms extends BaseAdapter {
    String alarmData[][];
    LayoutInflater inflater;
    Context c;
    Animation animRTL;
    public AdapterOfListAlarms(Context c,int layout,String [][]alarmData) {
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.c=c;
        this.alarmData=alarmData;
        animRTL= AnimationUtils.loadAnimation(c,R.anim.rtl_amin);
    }

    @Override
    public int getCount() {
        return alarmData.length;
    }

    @Override
    public String[] getItem(int position) {
        return alarmData[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    void removeItem(int index){
        /*data*/

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView =  this.inflater.inflate(R.layout.mainpagelayout, parent, false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.textNameOfAlarm);
        TextView time=(TextView) convertView.findViewById(R.id.textTimeOfAlarm);
        TextView AMOrBM=(TextView) convertView.findViewById(R.id.textAMOrBMOfAlarm);
        TextView alarmDay=(TextView) convertView.findViewById(R.id.textDaysOfAlarm);
        SwitchCompat switchAlarm=(SwitchCompat) convertView.findViewById(R.id.switchOFAlarm);

        String[] unitDataOfAlarm=alarmData[position];
        name.setText(unitDataOfAlarm[0]);
        time.setText(unitDataOfAlarm[1]);
        AMOrBM.setText(unitDataOfAlarm[2]);
        alarmDay.setText(unitDataOfAlarm[3]);
        if (unitDataOfAlarm[4].equals("on"))
            switchAlarm.setChecked(true);
        else
            switchAlarm.setChecked(false);

        Animation animRTL = AnimationUtils.loadAnimation(c,R.anim.rtl_amin);
        convertView.setAnimation(animRTL);

        if (!animRTL.hasStarted())
            animRTL.start();
        return convertView;

    }
}
