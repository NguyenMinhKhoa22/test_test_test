package com.example.room_database12;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "note")
public class MainEntity {

    // CREATE ID COLUMN
    @PrimaryKey(autoGenerate = true)
    private int id;

    // CREATE TEXT COLUMN
    @ColumnInfo(name = "stringone")
    private String stringone;

    // CREATE TEXT COLUMN
    @ColumnInfo(name = "stringtwo")
    private String stringtwo;


    //getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringone() {
        return stringone;
    }

    public void setStringone(String stringone) {
        this.stringone = stringone;
    }

    public String getStringtwo() {
        return stringtwo;
    }

    public void setStringtwo(String stringtwo) {
        this.stringtwo = stringtwo;
    }
}
