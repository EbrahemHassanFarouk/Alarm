package com.heemadev.alarm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collections;

public class AdapterOfListview extends BaseAdapter {
    public String selected = "";
    Context c;
    String[] data;
    LayoutInflater layout;
    ArrayList<String>dataList;

    public AdapterOfListview(Context c, String[] d) {
        this.c = c;
        data = d;
        layout = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataList=new ArrayList<>();
        Collections.addAll(dataList,d);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layout.inflate(R.layout.layout_of_listview, viewGroup, false);
        }
        RadioButton radioButton = view.findViewById(R.id.RadBtn);
        radioButton.setText(getItem(i));
        radioButton.setChecked(false);
        if (radioButton.getText().toString().equals("Basic call")){
            radioButton.setChecked(true);
            selected="Basic call";
        }
        if (!dataList.contains("Basic call")&&i==0)
        {
            radioButton.setChecked(true);
        }
        ListView listView = (ListView) viewGroup;
        radioButton.setOnClickListener(view1 -> {
            for (int i2 = 0; i2 < getCount(); i2++) {
                View view2 = listView.getChildAt(i2);
                RadioButton rad = view2.findViewById(R.id.RadBtn);
                if (i2 != i)
                    rad.setChecked(false);
                else {
                    rad.setChecked(true);
                    selected = rad.getText().toString();
                }
            }
            selected = radioButton.getText().toString();
            Log.e("TAG", "getView: "+selected );
        });




        return view;
    }
}
