package com.zjb.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class GroupAdapter extends ArrayAdapter<Group_list> {
    private int resourceID;
    public GroupAdapter(@NonNull Context context, int resource, @NonNull List<Group_list> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Group_list group_list=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView group_name=view.findViewById(R.id.group_name);
        TextView group_count=view.findViewById(R.id.group_count);
        group_name.setText(group_list.getGroup_name());
        group_count.setText(group_list.getGroup_count());
        return view;
    }
}
