package com.example.dailydo.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dailydo.R;

public class DetailTaskFragment extends Fragment {

  private TextView tvNamaTask, tvDeadline;
  private EditText etNamaTask, etDeadline;
  private Button btSave;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_detail_task, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    tvNamaTask = view.findViewById(R.id.tvNamaTask);
    tvDeadline = view.findViewById(R.id.tvDeadline);

    if (getArguments() != null) {
      tvNamaTask.setText(getArguments().getString("namaTask"));
      tvDeadline.setText(getArguments().getString("deadline"));
    }

    etNamaTask = view.findViewById(R.id.etNamaTask);
    etDeadline = view.findViewById(R.id.etDeadline);
    btSave = view.findViewById(R.id.btSave);
    btSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String idTask = getArguments().getString("idTask");
        String taskBaru = etNamaTask.getText().toString();
        String deadlineBaru = etDeadline.getText().toString();

        Task updateTask = new Task(idTask, taskBaru, deadlineBaru);

        AppDatabase database = AppDatabase.getInstance(getContext());
        database.taskDao().updateTask(updateTask);

        tvNamaTask.setText(taskBaru);
        tvDeadline.setText(deadlineBaru);

        etNamaTask.setText("");
        etDeadline.setText("");
      }
    });
  }
}
