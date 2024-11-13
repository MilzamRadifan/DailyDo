package com.example.dailydo.Category;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dailydo.Home.HomeActivity;
import com.example.dailydo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment {
  public CategoryFragment() {}

  private BottomNavigationView botNavbar;
  private RecyclerView recyclerView;
  private List<Category> categoryList = new ArrayList<>();
  private CategoryAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_category, container, false);

    recyclerView = view.findViewById(R.id.rvCategory);
    adapter = new CategoryAdapter(requireContext(), categoryList);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    fetchCategories();
    return view;
  }

  private void fetchCategories() {
    new Thread(() -> {
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("http://10.0.2.2/ApiDailyDo/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();

      ApiService apiService = retrofit.create(ApiService.class);
      Call<List<Category>> call = apiService.getCategories();

      call.enqueue(new Callback<List<Category>>() {
        @Override
        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
          if (response.isSuccessful() && response.body() != null) {
            categoryList.clear();
            categoryList.addAll(response.body());
            requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
          } else {
            Log.e("CategoryFragment", "Response failed: " + response.message());
          }
        }

        @Override
        public void onFailure(Call<List<Category>> call, Throwable t) {
          Log.e("CategoryFragment", "Error: " + t.getMessage());
          requireActivity().runOnUiThread(() ->
              Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show());
        }
      });
    }).start();
  }
}