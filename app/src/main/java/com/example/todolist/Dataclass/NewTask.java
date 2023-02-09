package com.example.todolist.Dataclass;

public class NewTask {
  String taskName;
    String day;
    String cate;
public NewTask(String name){
    this.taskName=name;

}
    public NewTask(String taskName,String day,String cate) {
        this.taskName = taskName;
        this.day=day;
        this.cate=cate;
    }

    public String getTaskName() {
        return taskName;
    }
    public String getDay() {
        return day;
    }
    public String getCate() {
        return cate;
    }
}
