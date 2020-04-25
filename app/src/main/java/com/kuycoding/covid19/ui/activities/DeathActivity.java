package com.kuycoding.covid19.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.adapter.DeathAdapter;
import com.kuycoding.covid19.model.Death;
import com.kuycoding.covid19.viewModel.DeathViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeathActivity extends AppCompatActivity {
    private DeathAdapter adapter;
    private List<Death> list = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);

        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.rv_death);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DeathAdapter(this, list);
        recyclerView.setAdapter(adapter);

        DeathViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(DeathViewModel.class);
        viewModel.setDeath();
        viewModel.getIsLoad().observe(this, aBoolean -> {
            if (aBoolean != null) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        viewModel.getDeath().observe(this, deaths -> {
            if (deaths != null) {
                list.clear();
                list.addAll(deaths);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
