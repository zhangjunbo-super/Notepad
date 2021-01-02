package com.zjb.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class inf_update extends AppCompatActivity {

    EditText title;
    EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_update);
        final DBHelper dbHelper=DBDao.getDBHelper(getApplicationContext());
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        Button butt_sure = findViewById(R.id.butt_sure);
        Button butt_cancle = findViewById(R.id.butt_cancle);
        Button butt_setclock=findViewById(R.id.setclock);
        final Intent intent = getIntent();
        final String inf_id = intent.getStringExtra("inf_id");
        init(dbHelper,Integer.parseInt(inf_id));

        butt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_title=String.valueOf(title.getText());
                String new_content=String.valueOf(content.getText());
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=new Date(System.currentTimeMillis());
                System.out.println(simpleDateFormat.format(date));
                String sql="update sheet_informations set inf_title='"+new_title+"',inf_inf='"+new_content+"',inf_date='"+simpleDateFormat.format(date)+"' where inf_id="+Integer.parseInt(inf_id);
                dbHelper.Update(dbHelper.getWritableDatabase(),sql);
                Intent intent1=new Intent(inf_update.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        butt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(inf_update.this,MainActivity.class);
                startActivity(intent1);     
            }
        });
        butt_setclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                new TimePickerDialog(inf_update.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        calendar.add(Calendar.SECOND,10);
                        Intent alarmIntent=new Intent(AlarmClock.ACTION_SET_ALARM);
                        alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE,String.valueOf(title.getText()));
                        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR,i);

                        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,i1);

                        alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
                        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(alarmIntent);
                    }
                },calendar.get(calendar.get(Calendar.HOUR_OF_DAY)),calendar.get(Calendar.MINUTE)+1,true).show();

            }
        });


    }
    private void init(DBHelper dbHelper,int int_id){
        Cursor cursor=dbHelper.Select(dbHelper.getWritableDatabase(),"select * from sheet_informations where inf_id="+int_id);
        if(cursor.moveToFirst()){
            do {
                title.setText(cursor.getString(cursor.getColumnIndex("inf_title")));
                content.setText(cursor.getString(cursor.getColumnIndex("inf_inf")));
            }while (cursor.moveToNext());
        }
    }
}