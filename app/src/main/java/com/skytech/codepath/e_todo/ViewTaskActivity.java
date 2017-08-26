package com.skytech.codepath.e_todo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skytech.codepath.e_todo.db.DBHelper;
import com.skytech.codepath.e_todo.model.Etodo;

/**
 * Created by akash on 8/24/2017.
 */

public class ViewTaskActivity extends AppCompatActivity {

    TextView name,dueDate,notes,priority,status;
    Context context;
    Integer taskID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task_activity);

        context = getApplicationContext();

        name = (TextView)findViewById(R.id.viewTask_taskName);
        dueDate = (TextView)findViewById(R.id.viewTask_dueDate);
        notes = (TextView)findViewById(R.id.viewTask_taskNotes);
        priority = (TextView)findViewById(R.id.viewTask_priority);
        status = (TextView)findViewById(R.id.viewTask_status);

        taskID = getIntent().getExtras().getInt("itemID");
        loadTaskData();
    }

    private void loadTaskData(){
        DBHelper helper = new DBHelper(context);
        Etodo task = helper.getTaskById(taskID);
        name.setText(task.getName());
        dueDate.setText(task.getDueDate());
        notes.setText(task.getNotes());
        priority.setText(task.getPriority());
        status.setText(task.getStatus());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_view_task,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_editTask:
                Bundle bundle = new Bundle();
                bundle.putInt("taskID",taskID);
                bundle.putString("operation","edit");
                Intent intent = new Intent(context,AddTaskActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.menu_deleteTask: openDeleteDialog();
                return true;
            case R.id.menu_cancelTask :
                Intent intent2 = new Intent(context,MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void openDeleteDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.delete_dialog);
        dialog.setTitle("Delete Item");
        Button ok,cancel;
        ok = (Button)dialog.findViewById(R.id.ok);
        cancel = (Button)dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DBHelper helper = new DBHelper(context);
                helper.deleteTask(taskID);
                dialog.dismiss();
                Intent intent = new Intent(context,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}
