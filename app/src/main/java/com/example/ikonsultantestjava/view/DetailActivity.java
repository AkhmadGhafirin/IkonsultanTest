package com.example.ikonsultantestjava.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ikonsultantestjava.R;
import com.example.ikonsultantestjava.model.Post;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupUI();
    }

    @SuppressLint("SetTextI18n")
    private void setupUI() {
        Post item = (Post) getIntent().getSerializableExtra("item");
        TextView tvId = findViewById(R.id.tv_id);
        TextView tvUserId = findViewById(R.id.tv_user_id);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvBody = findViewById(R.id.tv_body);
        if (item != null) {
            tvId.setText("ID:\n" + item.getId());
            tvUserId.setText("User ID:\n" + item.getUserId());
            tvTitle.setText("Title:\n" + item.getTitle());
            tvBody.setText("Body:\n" + item.getBody());
        }
    }
}