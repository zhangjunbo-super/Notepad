package com.zjb.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class InfAdapter extends ArrayAdapter<Inf_list> {
    private int resourceID;
    public InfAdapter(@NonNull Context context, int resource, @NonNull List<Inf_list> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Inf_list inf_list=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView inf_title=view.findViewById(R.id.inf_title);
        TextView inf_content=view.findViewById(R.id.inf_content);
        TextView inf_time=view.findViewById(R.id.inf_time);
        inf_title.setText(inf_list.getInf_title());
        inf_content.setText(inf_list.getInf_inf());
        inf_time.setText(inf_list.getInf_date());
        return view;
    }
}
