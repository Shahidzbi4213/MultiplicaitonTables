package com.edu.pk.gulehri.xplytables;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.edu.pk.gulehri.xplytables.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolBar();
        binding.numRecycle.setLayoutManager(new GridLayoutManager(this, 4));
        binding.numRecycle.setAdapter(new NumAdapter());
    }

    @SuppressLint("SetTextI18n")
    private void setToolBar() {

        try {
            setSupportActionBar(binding.toolbarIncludeMain.mToolbar);
            binding.toolbarIncludeMain.toolbarText.setText("Multiplication Tables");
            binding.toolbarIncludeMain.mToolbar.setBackgroundColor(getResources().getColor(R.color.card_color));
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}