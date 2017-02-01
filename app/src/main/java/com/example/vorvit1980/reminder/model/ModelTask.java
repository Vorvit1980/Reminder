package com.example.vorvit1980.reminder.model;

/**
 * Created by vorvit1980 on 01.02.2017.
 */

public class ModelTask implements Item {

    private String title;
    private long date;

    public ModelTask(){

    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ModelTask(String title, long date){

        this.title=title;
        this.date=date;

    }

    @Override
    public boolean isTask() {
        return true;
    }
}
