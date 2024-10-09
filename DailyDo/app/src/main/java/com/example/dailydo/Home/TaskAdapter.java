package com.example.dailydo.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailydo.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.CategoryViewHolder> {

  private final Context context;
  private final List<Task> taskList;

  public TaskAdapter(Context context, List<Task> taskList) {
    this.context = context;
    this.taskList = taskList;
  }

  public static class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView tvJudul, tvJumlah;
    ImageView ivGambar;


    public CategoryViewHolder(@NonNull View itemView) {
      super(itemView);
      tvJudul = itemView.findViewById(R.id.tvJudul);
      tvJumlah = itemView.findViewById(R.id.tvJumlah);
      ivGambar = itemView.findViewById(R.id.ivGambar);
    }
  }

  @NonNull
  @Override
  public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(this.context)
        .inflate(R.layout.row_task, parent, false);
    return new CategoryViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    Task task = taskList.get(position);
    holder.tvJudul.setText(task.getJudul());
    holder.tvJumlah.setText(task.getTanggal());
    holder.ivGambar.setImageResource(task.getGambar());
    holder.ivGambar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(holder.itemView.getContext(), task.getKeterangan(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return taskList.size();
  }
}
