package com.example.ethereal.AlarmDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "myTable")
public class EntityClass {
    @PrimaryKey(autoGenerate = true)
    int id;
    String eventname;
    String eventdate;
    String eventtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }
}
