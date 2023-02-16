package com.example.todolist.Dataclass;

public class NewTask {
  String taskName;
    String day;
    String cate;
    String s;
public NewTask(){

}
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

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
