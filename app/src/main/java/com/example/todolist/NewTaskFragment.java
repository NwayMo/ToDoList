package com.example.todolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewTaskFragment extends Fragment {
    EditText editDate;
    DatePickerDialog.OnDateSetListener onDateSetListener;
Spinner spinner;
List<String> list;
ImageButton newListButton;
Toolbar toolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_new_task, container, false);
        ImageButton imageButton=view.findViewById(R.id.mic);
       editDate=view.findViewById(R.id.dateEdit);
       spinner=view.findViewById(R.id.spinner);
      newListButton=view.findViewById(R.id.newList);
        list=new ArrayList<>();
        list.addAll(Arrays.asList("Personal","Shopping","Wishlist","work"));
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(), R.layout.colorspinner_layout,list);
       adapter.setDropDownViewResource(R.layout.spinner_drop);
        spinner.setAdapter(adapter);

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
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater layoutInflater=getLayoutInflater();
                View view1=layoutInflater.inflate(R.layout.newtextdialog,null);
                builder.setView(view1)
                        .setTitle("New List")
                        .setPositiveButton("ADd", new DialogInterface.OnClickListener() {
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
           Calendar  calendar=Calendar.getInstance();
           int year=calendar.get(Calendar.YEAR);
           int month=calendar.get(Calendar.MONTH);
           int day=calendar.get(Calendar.DAY_OF_MONTH);
           DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),onDateSetListener,year,month,day);
           datePickerDialog.show();
       });
       onDateSetListener=new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               month=month+1;
               String date=dayOfMonth+"/"+month+"/"+year;
               editDate.setText(date);
           }
       };
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
        return view;
    }
}