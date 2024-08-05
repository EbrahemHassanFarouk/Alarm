package com.heemadev.alarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlOpenHelper extends SQLiteOpenHelper {

    public static final int  sqlVersion=1;
    public static final String sqlName="alarm.db";

    class table_vars{//{"ebrahem 1","1:00","AM","Every Day","on"},
        public static final String tableAlarmName="AlarmData";

        public static final String alarmName="AlarmName";
        public static final String alarmTime="AlarmTime";
        public static final String AlarmAMOrBM="AlarmAMOrBm";
        public static final String alarmDays="AlarmDays";
        public static final String alarmSwitch="AlarmSwitch";
        public static final String alarmRingtonePath="alarmRingtonePath";
        public static final String alarmVibration="alarmVibration";
        public static final String alarmInterval="alarmInterval";
        public static final String alarmRepeat="alarmRepeat";
        public static final String alarmRepeatCount="alarmRepeatCount";




    }

    public MySqlOpenHelper(@Nullable Context context) {
        super(context, sqlName, null, sqlVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQury="create table "
                +table_vars.tableAlarmName
                +"("+table_vars.alarmName +" text,"
                +table_vars.alarmTime+ " text,"
                +table_vars.AlarmAMOrBM+" text,"
                +table_vars.alarmDays+" text,"
                +table_vars.alarmSwitch+" text,"
                +table_vars.alarmRingtonePath+" text,"
                +table_vars.alarmVibration+" text,"
                +table_vars.alarmInterval+" text,"
                +table_vars.alarmRepeat+" text,"
                +table_vars.alarmRepeatCount+" Integer)";
        db.execSQL(createTableQury);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertInAlarmData(MySqlOpenHelper mySqlOpenHelper,ContentValues cV){
        SQLiteDatabase conection=mySqlOpenHelper.getWritableDatabase();
        return conection.insert(table_vars.tableAlarmName,null,cV);
    }
    public String[][] getAllAlarm(MySqlOpenHelper mySqlOpenHelper){
        SQLiteDatabase conection=mySqlOpenHelper.getReadableDatabase();
        Cursor values=conection.query(table_vars.tableAlarmName, null,null,null,null,null,null,null);
        String arrOfData[][]=new String[values.getCount()][5];
        int count=0;
        while (values.moveToNext()){
            arrOfData[count]= new String[]{values.getString(values.getColumnIndexOrThrow(table_vars.alarmName))
                    ,values.getString(values.getColumnIndexOrThrow(table_vars.alarmTime))
                    ,values.getString(values.getColumnIndexOrThrow(table_vars.AlarmAMOrBM))
                    ,values.getString(values.getColumnIndexOrThrow(table_vars.alarmDays))
                    ,values.getString(values.getColumnIndexOrThrow(table_vars.alarmSwitch))
            };
                    count++;
        }
        values.close();
        return arrOfData;
    }
}
