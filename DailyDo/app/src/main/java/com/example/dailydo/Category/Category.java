package com.example.dailydo.Category;

import java.util.List;

public class Category {

  private String categoryId;
  private String categoryName;
  private List<String> tasks;

  public Category(String categoryId, String categoryName, List<String> tasks) {
    super();
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.tasks = tasks;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public List<String> getTasks() {
    return tasks;
  }

  public void setTasks(List<String> tasks) {
    this.tasks = tasks;
  }

}