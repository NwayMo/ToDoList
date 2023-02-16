package com.example.todolist;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DirectAction;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todolist.Adapter.SpinnerAdapter;
import com.example.todolist.Adapter.SpinnerAdapter1;
import com.example.todolist.DataBase.CategoryDatabase;
import com.example.todolist.DataBase.NewTaskDatabase;
import com.example.todolist.Dataclass.NewTask;
import com.example.todolist.databinding.FragmentNewTaskBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class NewTaskFragment extends Fragment {
    FragmentNewTaskBinding binding;
    EditText nameTask,editDate,editTime,addNewTask;
    DatePickerDialog.OnDateSetListener onDateSetListener;
Spinner spinner,spinner1;

 public int b=0;
ArrayList<NewTask> list,list1;
ImageButton newListButton;
Toolbar toolbar;
ImageButton date,listButton,timeButton,cancelButton1;
TextView textView1,textView2,textView3,textView4;
ConstraintLayout constraintLayout,constraintLayout1;
NumberPicker numberPicker;
    FloatingActionButton fab;
    NewTaskDatabase db;
    CategoryDatabase cdb;
    String cate;
boolean check=false;
boolean isCheck=true;
//public  static NewTaskFragment newInstance(int title){
//    NewTaskFragment newTaskFragment=new NewTaskFragment();
//    Bundle bundle=new Bundle();
//    bundle.putInt("title",title);
//    newTaskFragment.setArguments(bundle);
//
//    return newTaskFragment;
//}
    private void OpenDate() { SimpleDateFormat sdf=new SimpleDateFormat("dd MM yyyy");

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), onDateSetListener, year, month, day);
        datePickerDialog.show();

    }

    private void  SetDate(){

        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;

                String date= dayOfMonth+"/"+month+"/"+year;
                ArrayList<String> dayName=new ArrayList<>();

                dayName.addAll(Arrays.asList("T","W","Th","F","Sa","Sun","Mon","T","W","Th","F","Sa","Sun","Mon","T","W","Th","F","Sa","Sun","Mon","T","W","Th","F","Sa","Sun","Mon","T","W","Th","F","Sa"));
                for (int i = dayOfMonth; i <dayName.size(); i++) {
                    String date1=dayName.get(i);
                    // editDate.setText(date1+month+year);
                    if(dayOfMonth== i){

                        editDate.setText(date1+","+month+","+year);

                    }else{

                    }

                }


            }

        };


    }
    private void OpenTime(){
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTime.setText(hourOfDay+ ":"+minute);
                if(db.saveTask("","","")==true){
                   // setNotificationTime(hourOfDay,minute);
                }

            }
        },hour,minute,true);
        timePickerDialog.setTitle("Choose Time");
        timePickerDialog.show();
    }
    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.alertdialog);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view1 = layoutInflater.inflate(R.layout.newtextdialog, null);
        addNewTask = view1.findViewById(R.id.category);
        builder.setView(view1)

                .setTitle("New List")
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String category = addNewTask.getText().toString();
                        String repeat=addNewTask.getText().toString();
                        cdb.saveCate(category);
                        cdb.saveRep(repeat);
                        Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();



                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_task, container, false);
        ImageButton imageButton = view.findViewById(R.id.mic);
        nameTask = view.findViewById(R.id.editText1);
        editDate = view.findViewById(R.id.editText2);
        editTime = view.findViewById(R.id.editText3);
timeButton=view.findViewById(R.id.timeEdit1);
cancelButton1=view.findViewById(R.id.cancel_button1);
        textView1 = view.findViewById(R.id.notification);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.addtolist);
        textView4=view.findViewById(R.id.repeat);
        constraintLayout = view.findViewById(R.id.constraint);
        constraintLayout1=view.findViewById(R.id.constraint1);

        db = new NewTaskDatabase(getContext());
        cdb = new CategoryDatabase(getContext());
        listButton = view.findViewById(R.id.newList);
        fab = view.findViewById(R.id.fab);
        spinner = view.findViewById(R.id.spinner);
        spinner1=view.findViewById(R.id.spinner1);
        newListButton = view.findViewById(R.id.newList);
        list = new ArrayList<>();
        list1=new ArrayList<>();
        list = cdb.getCate();
        list1=cdb.getRep();
        list1.add(new NewTask("No repeat"));
        list1.add(new NewTask("Once a Day"));
        list1.add(new NewTask("Once a Day(Mon-Fri"));
        list1.add(new NewTask("Once a Week"));
        list1.add(new NewTask("Once a Month"));
        list1.add(new NewTask("Once a Year"));
        list1.add(new NewTask("Other "));

        SpinnerAdapter spinnerAdapter=new SpinnerAdapter(list);
        SpinnerAdapter1 spinnerAdapter1=new SpinnerAdapter1(list1);
        spinner.setAdapter(spinnerAdapter);
        spinner1.setAdapter(spinnerAdapter1);


           // numberPicker.setValue(5);
           // numberPicker.setWrapSelectorWheel(true);

//spinner.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.ADD);
spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String i= String.valueOf(parent.getSelectedItemPosition());
        int a=list1.size()-1;
      view=LayoutInflater.from(parent.getContext()).inflate(R.layout.numberpicker,parent,false);
numberPicker=view.findViewById(R.id.numberPicker);

//int title=getArguments().getInt("title");
        numberPicker.setMaxValue(35);
        numberPicker.setMinValue(0);

        if(position==a){
//
AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
builder.setView(view)
                .setTitle("Repeat")
                        .setPositiveButton("SET", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list1.remove(a);
                              //  list1.add(a,new NewTask(String.valueOf(b)));
                               cdb.saveRep(String.valueOf(b));
                                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                                    @Override
                                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                        b=newVal;

                                        // cdb.saveRep(String.valueOf(newVal));
                                        Toast.makeText(getContext(), newVal, Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

        }
        Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        fab.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.newTask, new TaskListsFragment()).commit();
        });
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        newListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();

                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
            }


        });SetDate();
        editDate.setOnClickListener(v -> {

            OpenDate();

            date.setPadding(0, 0, 80, 0);


        });
//


      //  = view.findViewById(R.id.notification);
       textView1.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(v, "Want to customize notifications?" +
                            "Go to settings", Snackbar.LENGTH_LONG)
                    .setAction("SETTINGS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar snackbar1 = Snackbar.make(v, "message is restored", Snackbar.LENGTH_LONG);
                            snackbar1.show();

                        }
                    });
            snackbar.show();


notificationChannel();
           NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext(),Utils.CHANNEL_ID);
           builder.setSmallIcon(R.mipmap.ic_launcher)
                   .setContentTitle("Hay Noti")
                   .setContentText("Haha")
                   .setPriority(NotificationCompat.PRIORITY_HIGH)
                   .setAutoCancel(true);
           NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getContext());
           notificationManagerCompat.notify(Utils.NOTI_ID,builder.build());
            Toast.makeText(getContext(), "noti", Toast.LENGTH_SHORT).show();
        });
        date = view.findViewById(R.id.datePicker);


        ImageButton cancelButton = view.findViewById(R.id.cancel_button);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDate();
                check = true;
                cancelButton.setVisibility(View.VISIBLE);
                editTime.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
//        if(editTime.getVisibility()==View.VISIBLE){
                //  textView1.setVisibility(View.GONE);
                //textView2.setVisibility(View.GONE);
                textView1.setPadding(0, 100, 0, 0);
                textView2.setText("Day summary on th same day at 8:00AM");
                textView2.setPadding(0, 100, 0, 0);
                if (check == true) {

                    textView3.setPadding(0, 400, 0, 0);
                    constraintLayout1.setVisibility(View.VISIBLE);
                   // textView3.setPadding(0, 200, 0, 0);
                    constraintLayout.setPadding(0, 400, 0, 0);
                    listButton.setPadding(0, 400, 0, 0);

                   // newListButton.setPadding(0, 280, 0, 0);
                    timeButton.setVisibility(View.VISIBLE);
                    timeButton.setPadding(65, 0, 0, 0);
                    timeButton.setPadding(0, 0, 0, 0);
                }

//        }
                date.setPadding(0, 0, 185, 0);
                date.setPadding(0, 0, 80, 0);
                // getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

//   if (editText.getText().length()==0){
//       cancelButton.setVisibility(View.GONE);
//   }else{
//       cancelButton.setVisibility(View.VISIBLE);
//
//   }
            }

        });  //SetDate();



        cancelButton.setOnClickListener(v -> {
            cancelButton.setVisibility(View.GONE);
            editTime.setVisibility(View.GONE);
            textView4.setVisibility(View.GONE);
            textView1.setPadding(0, 0, 0, 100);
            textView2.setPadding(0, 0, 0, 100);
            textView2.setText(R.string.notification);
            date.setPadding(0, 0, 0, 0);
            editDate.getText().clear();
            editTime.getText().clear();
            if (check = true) {
                textView3.setPadding(0, 0, 0, 300);
                constraintLayout.setPadding(0, 0, 0, 300);
                constraintLayout1.setVisibility(View.GONE);


                //  listButton.setPadding(0,0,0,300);
                newListButton.setPadding(0, 0, 0, 280);
                cancelButton1.setVisibility(View.GONE);
                timeButton.setVisibility(View.GONE);
            }


        });
        timeButton.setOnClickListener(v -> {
            OpenTime();
            //timeButton.setPadding(0,0,185,0);
            timeButton.setPadding(0, 0, 85, 0);
            cancelButton1.setVisibility(View.VISIBLE);
        });
        editTime.setOnClickListener(v -> {
            //OpenTime();
        });
        cancelButton1.setOnClickListener(v -> {
            cancelButton1.setVisibility(View.GONE);
            editTime.getText().clear();
            timeButton.setPadding(65, 0, 0, 0);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            NewTask newTask=list.get(position);
            cate=newTask.getTaskName();




            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fab.setOnClickListener(v->{
            String name=nameTask.getText().toString();
            String time=String.valueOf(editTime.getText());
            int i=time.indexOf(":");
            setNotificationTime(Integer.parseInt(time.substring(0,i)),Integer.parseInt(time.substring(i+1)));
            String day= String.valueOf(editDate.getText());
            if(time.length()==0 && nameTask.getText().length()!=0 ){
                db.saveTask(name,day,cate);
            }else if (nameTask.getText().length()!=0){
                String day1= String.valueOf(editDate.getText()).concat(",").concat(time);
               db.saveTask(name,day1,cate);

                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("h").replace(R.id.newTask,new TaskListsFragment()).commit();

            }else{

                Toast.makeText(getContext(), "Enter task at first", Toast.LENGTH_SHORT).show();

            }




        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void notificationChannel(){
        NotificationChannel channel=new NotificationChannel(Utils.CHANNEL_ID,Utils.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(Utils.CHANNEL_DESC);
        NotificationManager manager=getActivity().getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }
  public void setNotificationTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        long futureInMillis = calendar.getTimeInMillis() - System.currentTimeMillis();
        if (futureInMillis < 0) {
            futureInMillis += AlarmManager.INTERVAL_DAY;
        }

        Intent intent = new Intent(getContext(), Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + futureInMillis, pendingIntent);
    }
}


