package com.example.todolist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todolist.Dataclass.NewTask;

import java.util.ArrayList;

public class NewTaskDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newTask.db";
SQLiteDatabase database;
    public NewTaskDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("CREATE TABLE nts(task TEXT,day TEXT,cate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS nts");
onCreate(db);
    }
    public boolean saveTask(String task,String day,String cate) {
        database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("task",task);
contentValues.put("day",day);
contentValues.put("cate",cate);

        database.insert("nts",null,contentValues);
  return true;
    }
    public ArrayList<NewTask> getTask(){
        database=this.getReadableDatabase();
        ArrayList<NewTask> list1=new ArrayList<>();
        Cursor cursor=database.rawQuery("select * from nts",null);
        while (cursor.moveToNext()){
            String name=cursor.getString(0);
String day=cursor.getString(1);
String cate=cursor.getString(2);
         NewTask task=new NewTask(name,day,cate);
            list1.add(task);
        }

        return list1;
    }
}
