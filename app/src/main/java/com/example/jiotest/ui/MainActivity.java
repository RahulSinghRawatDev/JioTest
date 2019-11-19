package com.example.jiotest.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.jiotest.R;
import com.example.jiotest.ViewModel.MainViewModel;
import com.example.jiotest.adapter.TaskAdapter;
import com.example.jiotest.databinding.ActivityMainBinding;
import com.example.jiotest.model.Task;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.init();
        mainViewModel.getData().observe(this, new Observer<List<Task>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<Task> tasks) {
                mAdapter = new TaskAdapter(tasks);
                binding.rcView.addItemDecoration(new DividerItemDecoration(binding.rcView.getContext(), DividerItemDecoration.VERTICAL));
                binding.rcView.setAdapter(mAdapter);
                binding.txtSize.setText("list size "+ tasks.size());
            }
        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InputActivity.class));
                finish();
            }
        });
    }

}
