package com.example.dailydo.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailydo.R;

import java.util.ArrayList;
import java.util.UUID;

public class AddCategoryFragment extends Fragment {
  private EditText editTextCategoryName;
  private Button buttonSave;
  private AppDatabase appDatabase;

  public AddCategoryFragment() {}

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_add_category, container, false);

    editTextCategoryName = view.findViewById(R.id.editTextCategoryName);
    buttonSave = view.findViewById(R.id.buttonSave);

    appDatabase = Room.databaseBuilder(requireContext(), AppDatabase.class, "dailydo-db")
        .fallbackToDestructiveMigration()
        .build();

    buttonSave.setOnClickListener(v -> saveCategory());

    return view;
  }

  private void saveCategory() {
    String categoryName = editTextCategoryName.getText().toString().trim();
    if (!categoryName.isEmpty()) {
      Category category = new Category();
      category.setCategoryId(UUID.randomUUID().toString());
      category.setCategoryName(categoryName);
      category.setIcon("");
      category.setTasks(new ArrayList<>());

      new Thread(() -> {
        appDatabase.categoryDao().insertCategory(category);
        requireActivity().runOnUiThread(() -> {
          Toast.makeText(getContext(), "Kategori berhasil ditambahkan", Toast.LENGTH_SHORT).show();

          FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
          fragmentManager.popBackStack();
        });
      }).start();
    } else {
      Toast.makeText(getContext(), "Nama kategori tidak boleh kosong", Toast.LENGTH_SHORT).show();
    }
  }
}
