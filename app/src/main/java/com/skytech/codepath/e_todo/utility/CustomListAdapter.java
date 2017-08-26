package com.skytech.codepath.e_todo.utility;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.skytech.codepath.e_todo.R;
import com.skytech.codepath.e_todo.model.Etodo;

import java.util.List;

/**
 * Created by akash on 8/22/2017.
 */

public class CustomListAdapter extends ArrayAdapter<Etodo> implements Filterable {

    public final Context context;
    public List<Etodo> taskList;

    public CustomListAdapter(Context context,List<Etodo> taskList){
        super(context, R.layout.list_row_layout,taskList);
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.list_row_layout,parent,false);

        TextView priority = (TextView) view.findViewById(R.id.priority);
        TextView taskName = (TextView) view.findViewById(R.id.taskName);
        String taskPriority = taskList.get(position).getPriority();
        priority.setText(taskPriority);
        taskName.setText(taskList.get(position).getName());
        if(taskPriority.equals("High")){
            priority.setTextColor(Color.RED);
        }else if(taskPriority.equals("Medium")){
            priority.setTextColor(Color.YELLOW);
        }else{
            priority.setTextColor(Color.GREEN);
        }
        return view;
    }

}