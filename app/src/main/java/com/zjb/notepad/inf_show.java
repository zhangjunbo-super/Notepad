package com.zjb.notepad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class inf_show extends AppCompatActivity {
    private List<Inf_list> inf_lists=new ArrayList<>();
    DBHelper dbHelper=DBDao.getDBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_show);
        final ListView inf_list=findViewById(R.id.list_inf);
        FloatingActionButton button_add=findViewById(R.id.button_add);
        final Intent intent=getIntent();
        initInf_list((String) intent.getSerializableExtra("group_id"));
        InfAdapter infAdapter=new InfAdapter(this,R.layout.inf_list,inf_lists);
        inf_list.setAdapter(infAdapter);

        inf_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Inf_list inf_list1=inf_lists.get(position);
                Intent intent1=new Intent(inf_show.this,inf_update.class);
                intent1.putExtra("inf_id",inf_list1.getInf_id());
                startActivity(intent1);

            }
        });
        inf_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Inf_list inf_list1=inf_lists.get(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(inf_show.this);
                builder.setMessage("确定删除嘛？");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.Delete(dbHelper.getWritableDatabase(),"delete from sheet_informations where inf_id="+inf_list1.getInf_id());
                        dbHelper.Update(dbHelper.getWritableDatabase(),"update sheet_group set group_count=group_count-1 where group_id="+intent.getSerializableExtra("group_id"));
                        inf_lists.clear();
                        initInf_list((String) intent.getSerializableExtra("group_id"));
                        InfAdapter infAdapter=new InfAdapter(inf_show.this,R.layout.inf_list,inf_lists);
                        inf_list.setAdapter(infAdapter);
                        Intent intent1=new Intent(inf_show.this,MainActivity.class);
                        startActivity(intent1);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("取消了删除=啥都没干");
                    }
                });
                builder.create().show();
                System.out.println("长按长按长按长按长按长按长按长按长按长按长按");
                return true;
            }
        });


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(inf_show.this,insert_Inf.class);
                intent1.putExtra("flag","informations");
                intent1.putExtra("group_id",intent.getSerializableExtra("group_id"));
                startActivity(intent1);
            }
        });
    }

    private void initInf_list(String group_id){
        String sql="select * from sheet_informations where group_id="+Integer.parseInt(group_id);
        Cursor cursor=dbHelper.Select(dbHelper.getWritableDatabase(),sql);
        if(cursor.moveToFirst()){
            do {
                String inf_id=String.valueOf(cursor.getInt(cursor.getColumnIndex("inf_id")));
                String inf_title=cursor.getString(cursor.getColumnIndex("inf_title"));
                String inf_inf=cursor.getString(cursor.getColumnIndex("inf_inf"));
                String inf_date=cursor.getString(cursor.getColumnIndex("inf_date"));
                String gruop_id=String.valueOf(cursor.getInt(cursor.getColumnIndex("group_id")));
                Inf_list inf_list=new Inf_list(inf_id,inf_title,inf_inf,inf_date,gruop_id);
                this.inf_lists.add(inf_list);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

}