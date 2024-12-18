package com.example.ikonsultantestjava.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikonsultantestjava.R;
import com.example.ikonsultantestjava.model.Post;
import com.example.ikonsultantestjava.view.adapter.MainAdapter;
import com.example.ikonsultantestjava.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private MainAdapter adapter;
    private List<Post> originalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.fetchData();

        setupUI();
        subscribeObserver();
    }

    private void setupUI() {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(item -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });
        Button button = findViewById(R.id.btn_cari);
        EditText editText = findViewById(R.id.et_search);
        button.setOnClickListener(view -> filterList(editText.getText().toString()));
    }

    private void filterList(String query) {
        if (query.isEmpty() || query.isBlank()) {
            adapter.submitList(originalList);
            return;
        }
        List<Post> filtered = new ArrayList<>();
        for (Post item : originalList) {
            if (item.getTitle().contains(query)) {
                filtered.add(item);
            }
        }
        adapter.submitList(filtered);
    }

    private void subscribeObserver() {
        viewModel.posts.observe(this, posts -> {
            originalList.addAll(posts);
            adapter.submitList(posts);
        });
    }
}