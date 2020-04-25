package com.kuycoding.covid19.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.adapter.RecoveredAdapter;
import com.kuycoding.covid19.model.Recovered;
import com.kuycoding.covid19.viewModel.RecoveredViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecoveredActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecoveredAdapter adapter;
    private RecoveredViewModel viewModel;
    private List<Recovered> list = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovered);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.rc_recovered);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new RecoveredAdapter(list, this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(RecoveredViewModel.class);
        viewModel.setRecovered();
        viewModel.getLoad().observe(this, aBoolean -> {
            if (aBoolean != null) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        viewModel.getRecovered().observe(this, recovereds -> {
            if (recovereds != null) {
                list.addAll(recovereds);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
