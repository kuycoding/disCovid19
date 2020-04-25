package com.kuycoding.covid19.ui.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BaseEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.kuycoding.covid19.R;
import com.kuycoding.covid19.adapter.ProvinsiAdapter;
import com.kuycoding.covid19.model.provinsi.Provinsi;
import com.kuycoding.covid19.viewModel.ProvinsiIdViewModel;

import java.util.ArrayList;
import java.util.List;

public class CaseIndoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProvinsiAdapter adapter;
    private ProvinsiIdViewModel viewModel;
    private List<Provinsi> listProvinsi = new ArrayList<>();
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private BarChart barChart;
    private TextView info;
    private LinearLayout lldata;


    private static final int[] COLOR_CUSTOM = {
            rgb("#b9aaf7"), rgb("#8aca2b"), rgb("#7d62ef")
    };

    private static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color) & 0xFF;
        return Color.rgb(r, g, b);
    }

    private TextView tvRecovered, tvDeath, tvConfirmed, tvCasePerProv, tvProvinsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_indo);
        progressBar = findViewById(R.id.progressBar);

        info = findViewById(R.id.txt_info);
        tvProvinsi = findViewById(R.id.txt_province);
        tvRecovered = findViewById(R.id.txt_recovered);
        tvDeath = findViewById(R.id.txt_death);
        tvConfirmed = findViewById(R.id.txt_confirmed);
        barChart = findViewById(R.id.bar_chart);
        tvCasePerProv = findViewById(R.id.case_per_prov);
        lldata = findViewById(R.id.layout_data);

        loadData();
        loadGraphics();
    }

    private void loadGraphics() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ProvinsiIdViewModel.class);
        viewModel.setListProvinsi();
        viewModel.getListProvinsi().observe(this, provinsis -> {
            info.setVisibility(View.VISIBLE);
            tvCasePerProv.setVisibility(View.VISIBLE);

            ArrayList<BarEntry> barEntries = new ArrayList<>();
            ArrayList<BarDataSet> barDataSets = new ArrayList<>();

            final List<Provinsi> provinsiList = new ArrayList<>();
            for (int i = 0; i < listProvinsi.size(); i++) {
                Provinsi provinsi = listProvinsi.get(i);
                float positif = Float.parseFloat(provinsi.getAttributes().getKasusPosi());
                float meninggal = Float.parseFloat(provinsi.getAttributes().getKasusMeni());
                float sembuh = Float.parseFloat(provinsi.getAttributes().getKasusSemb());

                barEntries.add(new BarEntry(i, positif));
                barEntries.add(new BarEntry(i, meninggal));
                barEntries.add(new BarEntry(i, sembuh));

                Provinsi one = listProvinsi.get(i);
                BaseEntry baseEntry = barEntries.get(i);
                barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        lldata.setVisibility(View.INVISIBLE);
                        tvProvinsi.setText(one.getAttributes().getProvinsi());
                        tvConfirmed.setText("Positif : " + one.getAttributes().getKasusPosi());
                        tvDeath.setText("Meninggal : " + one.getAttributes().getKasusMeni());
                        tvRecovered.setText("Sembuh : " +one.getAttributes().getKasusSemb());
                    }

                    @Override
                    public void onNothingSelected() {

                    }
                });
            }

            ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
            BarDataSet dataSet = new BarDataSet(barEntries, getResources().getString(R.string.covid_19));
            dataSet.setColors(COLOR_CUSTOM);
            dataSet.setDrawValues(false);

            iBarDataSets.add(dataSet);
            barDataSets.add(dataSet);

            Legend legend = barChart.getLegend();
            legend.setTextColor(Color.BLACK);
            legend.setTextSize(12);
            legend.setForm(Legend.LegendForm.SQUARE);

            BarData barData = new BarData(iBarDataSets);
            barData.setDrawValues(false);

            barChart.animateX(1500);
            barChart.setMaxVisibleValueCount(40);
            barChart.getXAxis().isEnabled();

            barChart.getLegend().setTextColor(R.color.grey_md_50);
            barChart.getAxisLeft().setTextColor(R.color.grey_md_50);
            barChart.getAxisRight().setTextColor(R.color.grey_md_50);
            barChart.getAxisLeft().enableGridDashedLine(10f, 10f, 2f);
            barChart.getAxisRight().enableGridDashedLine(10f, 10f, 2f);
            barChart.getXAxis().enableGridDashedLine(10f, 10f, 2f);

            barChart.notifyDataSetChanged();
            barChart.invalidate();
            barChart.setDescription(null);
            barChart.setData(barData);
        });
    }

    private void loadData() {
        recyclerView = findViewById(R.id.rv_provinsi);
        adapter = new ProvinsiAdapter(listProvinsi, this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ProvinsiIdViewModel.class);
        viewModel.getListProvinsi().observe(this, provinsis -> {
            if (provinsis != null) {
                listProvinsi.clear();
                listProvinsi.addAll(provinsis);
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
        viewModel.setListProvinsi();
    }
}
