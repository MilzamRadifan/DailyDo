package com.example.dailydo.Category;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailydo.Home.HomeActivity;
import com.example.dailydo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

  BottomNavigationView botNavbar;
  RecyclerView recyclerView;
  RecyclerView.LayoutManager layoutManager;
  List<ListCategory> categoryList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_category);

    List<ListCategory> categoryList = new ArrayList<>();
    categoryList.add(new ListCategory("Sekolah"));
    categoryList.add(new ListCategory("Kantor"));
    categoryList.add(new ListCategory("Rumah Sakit"));
    categoryList.add(new ListCategory("Restoran"));
    categoryList.add(new ListCategory("Toko"));

    recyclerView = findViewById(R.id.rvCategory);
    CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(),categoryList);
    recyclerView.setAdapter(adapter);
    layoutManager = new GridLayoutManager(CategoryActivity.this, 2);
    recyclerView.setLayoutManager(layoutManager);

    botNavbar= findViewById(R.id.bottomNav);
    botNavbar.setSelectedItemId(R.id.category);
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