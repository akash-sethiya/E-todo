package com.skytech.codepath.e_todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.skytech.codepath.e_todo.db.DBHelper;
import com.skytech.codepath.e_todo.model.Etodo;
import com.skytech.codepath.e_todo.utility.CustomSpinnerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash on 8/22/2017.
 */

public class AddTaskActivity extends AppCompatActivity {

    private Context context;
    EditText taskName,dueDate,taskNotes;
    Spinner priority,status;
    ArrayAdapter<String> priorityListAdapter,statusListAdapter;
    static final List<String> priorityItems = new ArrayList<String>();
    static final List<String> statusItems = new ArrayList<String>();
    String operation = null;
    Integer taskID = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        context = getApplicationContext();
        buildPriorityAndStatusItems();

        taskName = (EditText)findViewById(R.id.editText_taskName);
        dueDate = (EditText)findViewById(R.id.editText_dueDate);
        taskNotes = (EditText)findViewById(R.id.editText_taskNotes);
        priority = (Spinner) findViewById(R.id.spinner_priority);
        status = (Spinner) findViewById(R.id.spinner_status);

        priorityListAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,priorityItems);
        priority.setAdapter(priorityListAdapter);

        statusListAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,statusItems);
        status.setAdapter(statusListAdapter);

        operation = getIntent().getExtras().getString("operation");
        taskID = getIntent().getExtras().getInt("taskID");
        if(operation!= null && operation.equals("edit")){
            DBHelper helper = new DBHelper(context);
            Etodo task = helper.getTaskById(taskID);
            taskName.setText(task.getName());
            dueDate.setText(task.getDueDate());
            taskNotes.setText(task.getNotes());
            priority.setSelection(priorityItems.indexOf(task.getPriority()));
            status.setSelection(statusItems.indexOf(task.getStatus()));
        }

        priority.setOnItemSelectedListener(new CustomSpinnerListener());
        status.setOnItemSelectedListener(new CustomSpinnerListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_task,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_saveTask :
                DBHelper helper = new DBHelper(context);
                Etodo task = new Etodo();
                task.setName(taskName.getText().toString());
                task.setDueDate(dueDate.getText().toString());
                task.setNotes(taskNotes.getText().toString());
                task.setPriority(priority.getSelectedItem().toString());
                task.setStatus(status.getSelectedItem().toString());
                if(operation!=null && operation.equals("edit")){
                    task.setId(taskID);
                    helper.updateTask(task);
                }else{
                    helper.addTodoTask(task);
                }
                Intent intent1 = new Intent(context,MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                return true;
            case R.id.menu_cancelTask :
                Intent intent2 = new Intent(context,MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                return true;
            default : return super.onOptionsItemSelected(item);
        }
    }

    public void buildPriorityAndStatusItems(){
        if(priorityItems.size() == 0 || priorityItems.isEmpty()){
            priorityItems.add("High");
            priorityItems.add("Medium");
            priorityItems.add("Low");
        }

        if(statusItems.size() == 0 || statusItems.isEmpty()){
            statusItems.add("ToDo");
            statusItems.add("Done");
        }

    }
}


