package com.example.dailydo.Home;

import com.google.gson.annotations.SerializedName;

public class Task {
  @SerializedName("idTask")
  private String idTask;

  @SerializedName("namaTask")
  private String namaTask;

  @SerializedName("deadline")
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