package sample;


import java.sql.Timestamp;

public class Task {

    private int task_ID;
    private int user_ID;
    private Timestamp start_date_time;
    private Timestamp end_date_time;
    private int duration;
    private String title;
    private String description_of_task;

    public Task(int task_ID, int user_ID, Timestamp start_date_time, Timestamp end_date_time, int duration, String title, String description_of_task) {
        this.task_ID = task_ID;
        this.user_ID = user_ID;
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;
        this.duration = duration;
        this.title = title;
        this.description_of_task = description_of_task;
    }

    public int getTask_ID() {
        return task_ID;
    }

    public void setTask_ID(int task_ID) {
        this.task_ID = task_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public Timestamp getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(Timestamp start_date_time) {
        this.start_date_time = start_date_time;
    }

    public Timestamp getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(Timestamp end_date_time) {
        this.end_date_time = end_date_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription_of_task() {
        return description_of_task;
    }

    public void setDescription_of_task(String description_of_task) {
        this.description_of_task = description_of_task;
    }
}
