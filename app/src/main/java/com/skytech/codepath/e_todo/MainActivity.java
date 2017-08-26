package com.skytech.codepath.e_todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.skytech.codepath.e_todo.db.DBHelper;
import com.skytech.codepath.e_todo.model.Etodo;
import com.skytech.codepath.e_todo.utility.CustomListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchTaskInput;
    private ListView todoItems;
    private List<Etodo> items;
    private CustomListAdapter listAdapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getBaseContext();
        displayToDoItem();
        searchTaskInput = (EditText)findViewById(R.id.searchTaskInput);
        todoItems = (ListView)findViewById(R.id.todoItems);
        todoItems.setAdapter(listAdapter);
        todoItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("itemID",items.get(position).getId());
                Intent intent = new Intent(context,ViewTaskActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        searchTaskInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence searchSTR, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence searchSTR, int i, int i1, int i2) {
                String searchQuery = searchSTR.toString().toLowerCase();
                List<Etodo> tempList = new ArrayList<Etodo>();
                for(Etodo item:items){
                    if(item.getName().toLowerCase().contains(searchQuery) || item.getNotes().toLowerCase().contains(searchQuery)){
                        tempList.add(item);
                    }
                }

                listAdapter = new CustomListAdapter(context,tempList);
                todoItems.setAdapter(listAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public void displayToDoItem(){
        DBHelper helper = new DBHelper(context);
        items = helper.getAllTasks();
        listAdapter = new CustomListAdapter(context,items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_activity,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_addTask:
                Bundle bundle = new Bundle();
                bundle.putString("operation","add");
                Intent intent = new Intent(context,AddTaskActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
