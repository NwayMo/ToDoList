package com.example.todolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DirectAction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.databinding.FragmentNewTaskBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewTaskFragment extends Fragment {
    FragmentNewTaskBinding binding;
    EditText editDate,editTime;
    DatePickerDialog.OnDateSetListener onDateSetListener;
Spinner spinner;
List<String> list;
ImageButton newListButton;
Toolbar toolbar;
ImageButton date,listButton;
TextView textView1,textView2,textView3;
ConstraintLayout constraintLayout;
boolean check=false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_new_task, container, false);
        ImageButton imageButton=view.findViewById(R.id.mic);
       editDate=view.findViewById(R.id.dateEdit);
       editTime=view.findViewById(R.id.timeEdit);
       textView1=view.findViewById(R.id.notification);
       textView2=view.findViewById(R.id.textView2);
       textView3=view.findViewById(R.id.addtolist);
       constraintLayout=view.findViewById(R.id.constraint);
listButton=view.findViewById(R.id.newList);
       spinner=view.findViewById(R.id.spinner);
      newListButton=view.findViewById(R.id.newList);
        list=new ArrayList<>();
        list.addAll(Arrays.asList("Personal","Shopping","Wishlist","work"));
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(), R.layout.colorspinner_layout,list);
       adapter.setDropDownViewResource(R.layout.spinner_drop);
        spinner.setAdapter(adapter);

FloatingActionButton floatingActionButton=view.findViewById(R.id.fab);
floatingActionButton.setOnClickListener(v->{
    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.newTask,new TaskListsFragment()).commit();
});
toolbar=view.findViewById(R.id.toolbar);
toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getActivity().onBackPressed();
    }
});
        newListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext(),R.style.alertdialog);
                LayoutInflater layoutInflater=getLayoutInflater();
                View view1=layoutInflater.inflate(R.layout.newtextdialog,null);
                builder.setView(view1)

                        .setTitle("New List")
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });
       editDate.setOnClickListener(v->{
           OpenDate();

           date.setPadding(0,0,80,0);
       });

        TextView notification=view.findViewById(R.id.notification);
        notification.setOnClickListener(v->{
            Snackbar snackbar=Snackbar.make(v,"Want to customize notifications?" +
                            "Go to settings",Snackbar.LENGTH_LONG)
                            .setAction("SETTINGS", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
Snackbar snackbar1=Snackbar.make(v,"message is restored",Snackbar.LENGTH_LONG);
snackbar1.show();

                                }
                            });
            snackbar.show();

            Toast.makeText(getContext(), "noti", Toast.LENGTH_SHORT).show();
        });
date=view.findViewById(R.id.datePicker);
EditText editText=view.findViewById(R.id.TaskName);

ImageButton cancelButton=view.findViewById(R.id.cancel_button);
date.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        OpenDate();check=true;
        cancelButton.setVisibility(View.VISIBLE);
        editTime.setVisibility(View.VISIBLE);
//        if(editTime.getVisibility()==View.VISIBLE){
          //  textView1.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
            textView1.setPadding(0,100,0,0);
            textView2.setText("Day summary on th same day at 8:00AM");
            textView2.setPadding(0,100,0,0);
            if(check==true){

                textView3.setPadding(0,300,0,0);
                constraintLayout.setPadding(0,300,0,0);

                listButton.setPadding(0,300,0,0);
            }

//        }
        date.setPadding(0,0,185,0);

       // getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

//   if (editText.getText().length()==0){
//       cancelButton.setVisibility(View.GONE);
//   }else{
//       cancelButton.setVisibility(View.VISIBLE);
//
//   }
    }

});
cancelButton.setOnClickListener(v->{
    cancelButton.setVisibility(View.GONE);
    editTime.setVisibility(View.GONE);
    textView1.setPadding(0,0,0,100);
    textView2.setPadding(0,0,0,100);
    textView2.setText(R.string.notification);
    date.setPadding(0,0,0,0);
    if(check=true){
        textView3.setPadding(0,0,0,300);
        constraintLayout.setPadding(0,0,0,300);

        listButton.setPadding(0,0,0,300);
    }


});
editTime.setOnClickListener(v->{

});

        return view;
    }

    private void OpenDate() {
        Calendar  calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),onDateSetListener,year,month,day);
        datePickerDialog.show();
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                editDate.setText(date);
            }
        };
    }
}