package com.example.todolist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todolist.Adapter.SpinnerAdapter;
import com.example.todolist.Dataclass.NewTask;

import java.util.ArrayList;

public class CategoryDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="cate.db";
    private static final String DATABASE_NAME1="repeat.db";
    SQLiteDatabase database;
    public CategoryDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cates(cate TEXT)");
        db.execSQL("CREATE TABLE repeats(repeat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cates");
        db.execSQL("DROP TABLE IF EXISTS repeats");
        onCreate(db);

    }
    public void saveCate(String cate) {
        database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("cate",cate);


        database.insert("cates",null,contentValues);
    }
    public void saveRep(String repeat) {
        database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("repeat",repeat);


        database.insert("repeats",null,contentValues);
    }
    public ArrayList<NewTask> getCate(){
        database=this.getReadableDatabase();
        ArrayList<NewTask> list1=new ArrayList<>();
        Cursor cursor=database.rawQuery("select * from cates",null);
        while (cursor.moveToNext()){
            String name=cursor.getString(0);

            NewTask task=new NewTask(name);
            list1.add(task);

        }

        return list1;
    }
    public ArrayList<NewTask> getRep(){
        database=this.getReadableDatabase();
        ArrayList<NewTask> list1=new ArrayList<>();
        Cursor cursor=database.rawQuery("select * from repeats",null);
        while (cursor.moveToNext()){
            String name=cursor.getString(0);

            NewTask task=new NewTask(name);
            list1.add(task);

        }

        return list1;
    }
}
