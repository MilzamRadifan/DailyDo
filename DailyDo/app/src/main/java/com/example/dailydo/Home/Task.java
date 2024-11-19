package com.example.dailydo.Home;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Task")
public class Task {

  @SerializedName("idTask")
  @PrimaryKey
  @ColumnInfo(name = "idTask")
  @NonNull
  private String idTask;

  @SerializedName("namaTask")
  @ColumnInfo(name = "namaTask")
  private String namaTask;

  @SerializedName("deadline")
  @ColumnInfo(name = "deadline")
  private String deadline;

  public Task(String idTask, String namaTask, String deadline) {
    this.idTask = idTask;
    this.namaTask = namaTask;
    this.deadline = deadline;
  }

  public String getIdTask() {
    return idTask;
  }

  public String getNamaTask() {
    return namaTask;
  }

  public String getDeadline() {
    return deadline;
  }
}