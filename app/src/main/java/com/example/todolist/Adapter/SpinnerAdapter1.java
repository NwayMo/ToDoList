package com.example.todolist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.todolist.Dataclass.NewTask;
import com.example.todolist.NewTaskFragment;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter1 extends BaseAdapter {
    ArrayList<NewTask> arrayList;
    public SpinnerAdapter1(ArrayList list){
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
       RepeatHolder holder;

        View repeatView=convertView;
        if(repeatView==null){
        repeatView= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_cate,parent,false);
            holder=new RepeatHolder();

            holder.repeat=repeatView.findViewById(R.id.textView4);
          repeatView.setTag(holder);
        }else{
            holder=(RepeatHolder) repeatView.getTag();
        }
        NewTask newTask=arrayList.get(position);
        holder.repeat.setText(newTask.getTaskName());
        NewTaskFragment newTaskFragment=new NewTaskFragment();






        return repeatView;
    }
    class RepeatHolder{
        private TextView repeat;
    }
}

