package com.zjb.notepad;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintTypedArray;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.idescout.sql.SqlScoutServer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Group_list> group_lists=new ArrayList<>();
    final DBHelper dbHelper = DBDao.getDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SqlScoutServer.create(this, getPackageName());//软件内查看数据库
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView list_group=findViewById(R.id.list_group);
        initGroup_list();
        GroupAdapter groupAdapter=new GroupAdapter(MainActivity.this,R.layout.group_list,group_lists);
        list_group.setAdapter(groupAdapter);

        list_group.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Group_list group_list=group_lists.get(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确定删除嘛？");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.Delete(dbHelper.getWritableDatabase(),"delete from sheet_informations where group_id="+Integer.parseInt(group_list.getGroup_id()));
                        dbHelper.Delete(dbHelper.getWritableDatabase(),"delete from sheet_group where group_id="+Integer.parseInt(group_list.getGroup_id()));
                        group_lists.clear();
                        initGroup_list();
                        GroupAdapter groupAdapter=new GroupAdapter(MainActivity.this,R.layout.group_list,group_lists);
                        list_group.setAdapter(groupAdapter);
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

        list_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Group_list group_list=group_lists.get(position);
                Intent intent=new Intent(MainActivity.this,inf_show.class);
                intent.putExtra("group_id",group_list.getGroup_id());
                startActivity(intent);
            }
        });



        FloatingActionButton button_add=findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,insert_Inf.class);
                intent.putExtra("flag","group");
                startActivity(intent);
            }
        });
    }
    private void initGroup_list(){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        String sql="select * from sheet_group";
        Cursor cursor=dbHelper.Select(sqLiteDatabase,sql);
        if(cursor.moveToFirst()){
            do {
                String group_name=cursor.getString(cursor.getColumnIndex("group_name"));
                String group_id=String.valueOf(cursor.getInt(cursor.getColumnIndex("group_id")));
                String gruop_count=String.valueOf(cursor.getInt(cursor.getColumnIndex("group_count")));
                Group_list group_list=new Group_list(group_name,group_id,gruop_count);
                this.group_lists.add(group_list);
            }while(cursor.moveToNext());
        }
    }

}