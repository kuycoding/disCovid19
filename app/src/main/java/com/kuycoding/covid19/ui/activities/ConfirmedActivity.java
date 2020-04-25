package com.kuycoding.covid19.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.adapter.ConfirmedAdapter;
import com.kuycoding.covid19.model.Confirmed;
import com.kuycoding.covid19.viewModel.ConfirmedViewModel;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedActivity extends AppCompatActivity {
    private ConfirmedAdapter adapter;
    private List<Confirmed> list = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed);

        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.rv_confirmed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConfirmedAdapter(list);
        recyclerView.setAdapter(adapter);

        ConfirmedViewModel viewModel = new ViewModelProvider(this, new
                ViewModelProvider.NewInstanceFactory()).get(ConfirmedViewModel.class);
        viewModel.setCountry();
        viewModel.getCountry().observe(this, confirmeds -> {
            if (confirmeds != null) {
                list.addAll(confirmeds);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.getIsLoad().observe(this, aBoolean -> {
            if (aBoolean != null) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
