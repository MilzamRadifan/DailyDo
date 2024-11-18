package com.example.dailydo.Category;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dailydo.Home.HomeActivity;
import com.example.dailydo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment {
  public CategoryFragment() {}
;
  private RecyclerView recyclerView;
  private List<Category> categoryList = new ArrayList<>();
  private CategoryAdapter adapter;
  private AppDatabase appDatabase;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_category, container, false);

    recyclerView = view.findViewById(R.id.rvCategory);
    adapter = new CategoryAdapter(requireContext(), categoryList);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    // Initialize database instance
    appDatabase = Room.databaseBuilder(requireContext(), AppDatabase.class, "dailydo-db")
        .fallbackToDestructiveMigration()
        .build();

    FloatingActionButton fabAddCategory = view.findViewById(R.id.fabAddCategory);
    fabAddCategory.setOnClickListener(v -> {
      FragmentManager fragmentManager = getParentFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, new AddCategoryFragment());
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
    });

    fetchCategories();
    return view;
  }

  private void fetchCategories() {
    new Thread(() -> {
      List<Category> savedCategories = appDatabase.categoryDao().getAllCategories();
      if (!savedCategories.isEmpty()) {
        categoryList.clear();
        categoryList.addAll(savedCategories);
        requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
      } else {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2/ApiDailyDo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getCategories();

        call.enqueue(new Callback<ResponseBody>() {
          @Override
          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
              try {
                String json = response.body().string();
                Gson gson = new Gson();

                Category[] categories = gson.fromJson(json, Category[].class);

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                  appDatabase.categoryDao().insertCategories(Arrays.asList(categories));

                  categoryList.clear();
                  categoryList.addAll(appDatabase.categoryDao().getAllCategories());
                  requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                });

              } catch (Exception e) {
                e.printStackTrace();
                Log.e("CategoryFragment", "Parsing error: " + e.getMessage());
              }
            } else {
              Log.e("CategoryFragment", "Response failed: " + response.message());
            }
          }

          @Override
          public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("CategoryFragment", "Error: " + t.getMessage());
            requireActivity().runOnUiThread(() ->
                Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show());
          }
        });
      }
    }).start();
  }
}