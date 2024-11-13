package com.example.dailydo.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dailydo.R;

public class DetailTaskFragment extends Fragment {

  private TextView tvNamaTask, tvDeadline;

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
  }
}
