package com.heemadev.alarm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity  {
    ListView listMainPage;
    TextView textAdd,textView_in_app_bar;
    String [][]alarmData;
    /*
     {
            {"ebrahem 1","1:00","AM","Every Day","on"},
            {"ebrahem 2","2:00","BM","Every Day","on"},
            {"ebrahem 3","3:00","AM","Every Day","on"},
            {"ebrahem 4","4:00","BM","EvDG Day","OFF"},
            {"ebrahem 5","5:00","AM","EDDG y","OFF"},
            {"ebrahem 6","6:00","BM","E y","on"},
            {"ebrahem 7","7:00","AM","RT DF","on"},
            {"ebrahem 8","8:00","BM","E D D","OFF"},
            {"ebrahem 4","4:00","BM","EvDG Day","OFF"},
            {"ebrahem 5","5:00","AM","EDDG y","OFF"},
            {"ebrahem 6","6:00","BM","E y","on"},
            {"ebrahem 7","7:00","AM","RT DF","on"},
            {"ebrahem 8","8:00","BM","E D D","OFF"},
            {"ebrahem 4","4:00","BM","EvDG Day","OFF"},
            {"ebrahem 5","5:00","AM","EDDG y","OFF"},
            {"ebrahem 6","6:00","BM","E y","on"},
            {"ebrahem 7","7:00","AM","RT DF","on"},
            {"ebrahem 8","8:00","BM","E D D","OFF"},
            {"ebrahem 4","4:00","BM","EvDG Day","OFF"},
            {"ebrahem 5","5:00","AM","EDDG y","OFF"},
            {"ebrahem 6","6:00","BM","E y","on"},
            {"ebrahem 7","7:00","AM","RT DF","on"},
            {"ebrahem 8","8:00","BM","E D D","OFF"},
            {"ebrahem 4","4:00","BM","EvDG Day","OFF"},
            {"ebrahem 5","5:00","AM","EDDG y","OFF"},
            {"ebrahem 6","6:00","BM","E y","on"},
            {"ebrahem 7","7:00","AM","RT DF","on"},
            {"ebrahem 8","8:00","BM","E D D","OFF"}
    };
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setTitleColor(getResources().getColor(R.color.text_color,this.getTheme()));

        listMainPage= findViewById(R.id.listMainPage);

        textView_in_app_bar=findViewById(R.id.textView_in_app_bar);

        MySqlOpenHelper mySqlOpenHelper=new MySqlOpenHelper(this);
        alarmData=mySqlOpenHelper.getAllAlarm(mySqlOpenHelper);

        AdapterOfListAlarms adapter=new AdapterOfListAlarms(this,R.layout.mainpagelayout, alarmData);
        listMainPage.setAdapter(adapter);
        listMainPage.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, AlarmData.class);
            intent.putExtra("position",position);
            startActivity(intent);
//            finish();
        });
        listMainPage.setOnItemLongClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "long", Toast.LENGTH_SHORT).show();
            
            return true;
        });
//        textAdd=findViewById(R.id.textAdd);
//        textAdd.setOnClickListener(v-> {
//            Intent intent = new Intent(MainActivity.this, AlarmData.class);
//            startActivity(intent);
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.add);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                Intent intent = new Intent(MainActivity.this, AlarmData.class);
                startActivity(intent);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}