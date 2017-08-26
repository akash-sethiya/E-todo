package com.skytech.codepath.e_todo.db;

/**
 * Created by akash on 8/22/2017.
 */

public class DBConstants {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "codepath_etodo";
    public static final String TABLE_ETODO = "etodo";
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "name";
    public static final String TASK_NOTES = "notes";
    public static final String TASK_DUEDATE = "due_date";
    public static final String TASK_PRIORITY = "priority";
    public static final String TASK_STATUS = "status";
    public static final String ETODO_CREATE_TABLE = "CREATE TABLE " + TABLE_ETODO + "(" + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TASK_NAME +
            " TEXT," + TASK_NOTES + " TEXT," + TASK_DUEDATE + " TEXT," + TASK_PRIORITY + " TEXT," + TASK_STATUS + " TEXT)";
    public static final String ETODO_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_ETODO;
}
