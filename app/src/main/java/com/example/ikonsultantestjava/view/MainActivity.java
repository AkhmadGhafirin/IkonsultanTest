package com.example.ikonsultantestjava.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikonsultantestjava.R;
import com.example.ikonsultantestjava.view.adapter.MainAdapter;
import com.example.ikonsultantestjava.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.fetchData();

        setupUI();
        subscribeObserver();
    }

    private void setupUI() {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this);
        adapter.setClickListener(item -> {
            startActivity(
                    new Intent(this, DetailActivity.class)
            );
        });
    }

    private void subscribeObserver() {
        viewModel.posts.observe(this, posts -> {
            adapter.submitList(posts);
        });
    }
}