package com.example.dailydo.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    Button btDel;


    public CategoryViewHolder(@NonNull View itemView) {
      super(itemView);
      tvJudul = itemView.findViewById(R.id.tvJudul);
      tvJumlah = itemView.findViewById(R.id.tvJumlah);
      ivGambar = itemView.findViewById(R.id.ivGambar);
      btDel = itemView.findViewById(R.id.btDel);
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
    holder.tvJudul.setText(task.getNamaTask());
    holder.tvJumlah.setText(task.getDeadline());
    holder.ivGambar.setImageResource(R.drawable.home_icon);

    holder.itemView.setOnClickListener(v -> {
      Bundle bundle = new Bundle();
      bundle.putString("idTask", task.getIdTask());
      bundle.putString("namaTask", task.getNamaTask());
      bundle.putString("deadline", task.getDeadline());

      DetailTaskFragment fragment = new DetailTaskFragment();
      fragment.setArguments(bundle);

      ((HomeActivity) v.getContext()).getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.fragment_container, fragment)
          .addToBackStack(null)
          .commit();
    });

    holder.btDel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AppDatabase db = AppDatabase.getInstance(context);
        db.taskDao().deleteTask(task.getIdTask());
        taskList.remove(holder.getAdapterPosition());
        notifyItemRemoved(holder.getAdapterPosition());
        Toast.makeText(context, "Task Berhasil dihapus", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return taskList.size();
  }
}
