package com.mynotes.save.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Defines this class as a Room entity with "note_table" as the table name
@Entity(tableName = "note_table")
public class Note {

    // Marks 'id' as the primary key and auto-generates its value
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Setter for id (Room needs this to set the generated ID)
    public void setId(int id) {
        this.id = id;
    }

    // Getters for Room to access the data
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}