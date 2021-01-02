package com.zjb.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class insert_Inf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__inf);
        final EditText title=findViewById(R.id.title);
        final EditText inf_content=findViewById(R.id.content);
        Button finish=findViewById(R.id.finish);
        Button cancel=findViewById(R.id.cancel);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper helper = DBDao.getDBHelper(getApplicationContext());
                String name=String.valueOf(title.getText());
                String content=String.valueOf(inf_content.getText());
                Intent getflag=getIntent();
                String flag=getflag.getStringExtra("flag");
                String sql;
                if(flag.equals("group")){
                    sql="insert into sheet_group (group_name, group_count) values('"+name+"', 0)";
                    helper.Insert(helper.getWritableDatabase(), sql);

                }else if(flag.equals("informations")){
                    String group_id=getIntent().getStringExtra("group_id");
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date=new Date(System.currentTimeMillis());
                    System.out.println(simpleDateFormat.format(date));
                    sql="insert into sheet_informations (inf_title, inf_inf,inf_date,group_id) values('"+name+"','"+content+"','"+simpleDateFormat.format(date)+"',"+Integer.parseInt(group_id)+")";
                    helper.Insert(helper.getWritableDatabase(), sql);
                    helper.Update(helper.getWritableDatabase(),"update sheet_group set group_count=group_count+1 where group_id="+Integer.parseInt(group_id));
                }

                Intent intent=new Intent(insert_Inf.this,MainActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(insert_Inf.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}