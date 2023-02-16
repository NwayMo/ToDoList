package com.example.todolist.Adapter;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Dataclass.NewTask;
import com.example.todolist.MainActivity;
import com.example.todolist.R;

import java.util.Calendar;
import java.util.List;

public class TaskListsAdapter extends RecyclerView.Adapter<TaskListsAdapter.MyViewHolder>{
 List<NewTask> list;
 Context context;


 public TaskListsAdapter(List list,Context context){
     this.list=list;
     this.context=context;

 }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recyclerview_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
NewTask obj=list.get(position);
holder.textView1.setText(obj.getTaskName());
holder.textView2.setText(obj.getDay());
holder.textView3.setText(obj.getCate());

NewTask task=new NewTask();
task.setS(obj.getTaskName());
if(holder.getAdapterPosition()==0){
    holder.deleteButton.setVisibility(View.GONE);
    holder.editButton.setVisibility(View.GONE);
}

holder.deleteButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog dialog=new AlertDialog.Builder(context,R.style.alertdialog).create();
        dialog.setTitle("Are you sure?");

        dialog.setMessage("All tasks from the list will also be\n" +
                "deleted");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
//        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
//        LayoutInflater layoutInflater=LayoutInflater.from(context);
//        View view1=layoutInflater.inflate(R.layout.deletedialog,null);
//        builder.setView(view1)
//                .setTitle("Are you sure?")
//                .setMessage("All tasks from the list will also be\n" +
//                        "                      deleted")
//                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(v.getContext(), "ok", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show();
    }
});

holder.editButton.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        String str=holder.textView1.getText().toString();

        AlertDialog dialog=new AlertDialog.Builder(context,R.style.alertdialog).create();
        dialog.setTitle("Edit List");
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view1=layoutInflater.inflate(R.layout.edittask,null);
dialog.setView(view1);
EditText editText=view1.findViewById(R.id.editText);
editText.setText(str);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
//        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
//        LayoutInflater layoutInflater=LayoutInflater.from(context);
//        View view1=layoutInflater.inflate(R.layout.deletedialog,null);
//        builder.setView(view1)
//                .setTitle("Are you sure?")
//                .setMessage("All tasks from the list will also be\n" +
//                        "                      deleted")
//                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(v.getContext(), "ok", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show();
    }
});

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
     TextView textView1,textView2,textView3;

     ImageButton deleteButton,editButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.title);
            textView2=itemView.findViewById(R.id.task);
            textView3=itemView.findViewById(R.id.category);
            deleteButton=itemView.findViewById(R.id.delete);
            editButton=itemView.findViewById(R.id.editList);


        }
    }


}
