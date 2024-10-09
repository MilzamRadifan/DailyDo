package com.example.dailydo.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailydo.Category.CategoryActivity;
import com.example.dailydo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

  BottomNavigationView botNavbar;
  RecyclerView recyclerView;
  TaskAdapter taskAdapter;
  List<Task> taskList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_home);

    recyclerView = findViewById(R.id.rvTask);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    taskList = new ArrayList<>();
    taskList.add(new Task("Progress 2 mobile", "09 Oktober 2024", "Implementasi Recycler View", R.drawable.android));
    taskList.add(new Task("Beli makan kucing", "10 Oktober 2024", "Whiskas 2, cat choice 2", R.drawable.whiskas));
    taskList.add(new Task("Lomba mobile dev", "15 Oktober 2024", "Lomba di UGM mobile dev", R.drawable.medali));
    taskList.add(new Task("UTS", "16 Oktober 2024", "belajar materi bab 1 sampe 3", R.drawable.buku));
    taskList.add(new Task("Submit proposal", "20 Oktober 2024", "Proposal harus ngebenerin latar belakang", R.drawable.proposal));
    taskList.add(new Task("Jemput di bandara", "39 Oktober 2024", "Jemput di bandara jam 2 siang, JGN LUPA", R.drawable.pesawat));
    taskList.add(new Task("Latian MC", "22 Oktober", "Latian di gkm jam 7 malem", R.drawable.mc));

    taskAdapter = new TaskAdapter(this, taskList);
    recyclerView.setAdapter(taskAdapter);

    botNavbar= findViewById(R.id.bottomNav);
    botNavbar.setSelectedItemId(R.id.home);
    botNavbar.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.home){
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
      } else if(item.getItemId() == R.id.category){
        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        finish();
      }
      return false;
    });
  }
}