package com.skytech.codepath.e_todo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.skytech.codepath.e_todo.model.Etodo;

import java.util.ArrayList;
import java.util.List;

import static com.skytech.codepath.e_todo.db.DBConstants.DATABASE_NAME;
import static com.skytech.codepath.e_todo.db.DBConstants.DATABASE_VERSION;
import static com.skytech.codepath.e_todo.db.DBConstants.ETODO_CREATE_TABLE;
import static com.skytech.codepath.e_todo.db.DBConstants.ETODO_DROP_TABLE;
import static com.skytech.codepath.e_todo.db.DBConstants.TABLE_ETODO;
import static com.skytech.codepath.e_todo.db.DBConstants.TASK_DUEDATE;
import static com.skytech.codepath.e_todo.db.DBConstants.TASK_ID;
import static com.skytech.codepath.e_todo.db.DBConstants.TASK_NAME;
import static com.skytech.codepath.e_todo.db.DBConstants.TASK_NOTES;
import static com.skytech.codepath.e_todo.db.DBConstants.TASK_PRIORITY;
import static com.skytech.codepath.e_todo.db.DBConstants.TASK_STATUS;

/**
 * Created by akash on 8/22/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        deleteAll(db);
        db.execSQL (ETODO_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL (ETODO_DROP_TABLE);
        onCreate(db);
    }

    public void addTodoTask(Etodo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME,todo.getName());
        values.put(TASK_NOTES,todo.getNotes());
        values.put(TASK_DUEDATE,todo.getDueDate());
        values.put(TASK_PRIORITY,todo.getPriority());
        values.put(TASK_STATUS,todo.getStatus());

        db.insert(TABLE_ETODO,null,values);
        db.close();
    }

    public List<Etodo> getAllTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Etodo> tasks = new ArrayList<Etodo>();
        String query = "SELECT * FROM " + TABLE_ETODO;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Etodo task = new Etodo();
                task.setId(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setNotes(cursor.getString(2));
                task.setDueDate(cursor.getString(3));
                task.setPriority(cursor.getString(4));
                task.setStatus(cursor.getString(5));
                tasks.add(task);
            }while(cursor.moveToNext());
        }
        db.close();
        return tasks;
    }

    public List<Etodo> searchTask(String searchSTR){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Etodo> tasks = new ArrayList<Etodo>();
        String query = "SELECT * FROM " + TABLE_ETODO + " WHERE " + TASK_NAME + " LIKE '%" + searchSTR + "%' OR " + TASK_NOTES + " LIKE '%" + searchSTR + "%'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Etodo task = new Etodo();
                task.setId(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setNotes(cursor.getString(2));
                task.setDueDate(cursor.getString(3));
                task.setPriority(cursor.getString(4));
                task.setStatus(cursor.getString(5));
                tasks.add(task);
            }while(cursor.moveToNext());
        }
        db.close();
        return tasks;
    }

    public void updateTask(Etodo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME,todo.getName());
        values.put(TASK_NOTES,todo.getNotes());
        values.put(TASK_DUEDATE,todo.getDueDate());
        values.put(TASK_PRIORITY,todo.getPriority());
        values.put(TASK_STATUS,todo.getStatus());
        db.update(TABLE_ETODO,values,TASK_ID + "= ?",new String[]{String.valueOf(todo.getId())});
        db.close();
    }

    public Etodo getTaskById(int taskID){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ETODO + " WHERE " + TASK_ID + " = " + taskID;
        Cursor cursor = db.rawQuery(query,null);
        Etodo task = new Etodo();
        if(cursor.moveToFirst()){
            task.setId(cursor.getInt(0));
            task.setName(cursor.getString(1));
            task.setNotes(cursor.getString(2));
            task.setDueDate(cursor.getString(3));
            task.setPriority(cursor.getString(4));
            task.setStatus(cursor.getString(5));
        }
        db.close();
        return task;
    }

    public void deleteTask(int taskID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ETODO,TASK_ID + " = ?",new String[]{String.valueOf(taskID)});
        db.close();
    }

    private void deleteAll(SQLiteDatabase db){
        db.execSQL(ETODO_DROP_TABLE);
    }
}
