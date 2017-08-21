package com.skytech.codepath.e_todo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText todoTask;
    private ListView todoItems;
    private List<String> items;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayToDoItem();
        todoTask = (EditText)findViewById(R.id.todoTask);
        todoItems = (ListView)findViewById(R.id.todoItems);
        todoItems.setAdapter(listAdapter);
        //items = new ArrayList<String>();
        todoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                items.remove(position);
                listAdapter.notifyDataSetChanged();
                writeToFile();
                return true;
            }
        });

        todoItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                showEditDialog(position);
                writeToFile();
            }
        });
    }

    public void showEditDialog(final int position){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_dialog);
        dialog.setTitle("Edit Item");
        final EditText editTodo = (EditText)dialog.findViewById(R.id.todoItemEdit);
        editTodo.setText(items.get(position));
        Button editToDoBTN,cancelDialogBTN;
        editToDoBTN = (Button)dialog.findViewById(R.id.editToDoBTN);
        cancelDialogBTN = (Button)dialog.findViewById(R.id.cancelDialogBTN);
        cancelDialogBTN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        editToDoBTN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                items.set(position,editTodo.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void readFile(){
        File dir = getFilesDir();
        File file = new File(dir,"todo.txt");
        try{
            items = new ArrayList<String>(FileUtils.readLines(file));
        }catch(IOException e){

        }
    }

    public void writeToFile(){
        File dir = getFilesDir();
        File file = new File(dir,"todo.txt");
        try{
            FileUtils.writeLines(file,items);
        }catch(IOException e){}

    }

    public void displayToDoItem(){
        items = new ArrayList<String>();
        readFile();
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
    }

    public void addItem(View view){
        String task = todoTask.getText().toString();
        listAdapter.add(task);
        todoTask.setText("");
        writeToFile();
    }
}
