package com.example.pillsmessaging.DataBasePills;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pills")
public class ItemPill {
    @Ignore
    public ItemPill(String description) {
        this.description = description;
    }

    public ItemPill(String time, String description) {
        this.description = description;
        this.time = time;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "checker")
    private boolean isAvailable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
