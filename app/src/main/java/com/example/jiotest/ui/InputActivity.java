package com.example.jiotest.ui;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.example.jiotest.InputViewModel;
import com.example.jiotest.R;
import com.example.jiotest.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInputBinding inputBinding = DataBindingUtil.setContentView(this, R.layout.activity_input);
        InputViewModel mainViewModel = ViewModelProviders.of(this).get(InputViewModel.class);
        inputBinding.setViewmodel(mainViewModel);
        mainViewModel.init();
        mainViewModel.getSaveMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(InputActivity.this,s,Toast.LENGTH_LONG).show();
                startActivity(new Intent(InputActivity.this,MainActivity.class));
                finish();
            }
        });

        mainViewModel.getValidationMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(InputActivity.this,s,Toast.LENGTH_LONG).show();
            }
        });
    }
}
