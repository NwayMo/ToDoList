package com.example.todolist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.Dataclass.NewTask;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    ArrayList<NewTask> arrayList;
public SpinnerAdapter(ArrayList <NewTask>list){
    this.arrayList=list;
}
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       CateHolder  holder;

        View cateView=convertView;
        if(cateView==null){
         cateView= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_cate,parent,false);
       holder=new CateHolder();

       holder.cateName=cateView.findViewById(R.id.textView4);
cateView.setTag(holder);
        }else{
            holder=(CateHolder) cateView.getTag();
        }
        NewTask newTask=arrayList.get(position);
        holder.cateName.setText(newTask.getTaskName());



        return cateView;
    }
    class CateHolder{

        private TextView cateName;
    }
}
