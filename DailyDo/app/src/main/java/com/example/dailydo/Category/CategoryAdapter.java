package com.example.dailydo.Category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dailydo.Category.Category;

import com.example.dailydo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

  private final Context context;
  private final List<Category> categoryList;

  public CategoryAdapter(Context context, List<Category> categoryList) {
    this.context = context;
    this.categoryList = categoryList;
  }

  public static class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView tvCategoryName;
    CardView cvCategory;

    public CategoryViewHolder(@NonNull View itemView) {
      super(itemView);
      cvCategory = itemView.findViewById(R.id.cvCategory);
      tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
    }
  }

  @NonNull
  @Override
  public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(this.context)
        .inflate(R.layout.row_category, parent, false);
    return new CategoryViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    Category category = categoryList.get(position);
    holder.tvCategoryName.setText(category.getCategoryName());
    holder.cvCategory.setOnClickListener(v -> {
      Intent intent = new Intent(context, DetailCategoryActivity.class);
      intent.putStringArrayListExtra("tasks", new ArrayList<>(category.getTasks()));

      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      holder.itemView.getContext().startActivity(intent);
    });
  }

  @Override
  public int getItemCount() {
    return categoryList.size();
  }
}
