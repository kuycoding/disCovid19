package com.kuycoding.covid19.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.kuycoding.covid19.R;
import com.kuycoding.covid19.model.CountryModel;
import com.kuycoding.covid19.ui.activities.CaseIndoActivity;
import com.kuycoding.covid19.ui.activities.ConfirmedActivity;
import com.kuycoding.covid19.ui.activities.DeathActivity;
import com.kuycoding.covid19.ui.activities.GlobalActivity;
import com.kuycoding.covid19.ui.activities.RecoveredActivity;
import com.kuycoding.covid19.viewModel.CountryViewModel;
import com.kuycoding.covid19.viewModel.GlobalViewModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ProgressDialog progressDialog;
    private DecimalFormat decim = new DecimalFormat("#,###.##");
    private LinearLayout layout_case;
    private TextView tvPositif;
    private TextView tvSembuh;
    private TextView tvMati;
    private TextView tvTanggal;
    private TextView tvCaseReported;

    private TextView tvIdRecovered;
    private TextView tvIdConfirmed;
    private TextView tvLocate;
    private TextView tvIdDeath;
    private TextView tvUpdate;

    private LinearLayout ll_global;
    private ConstraintLayout layout_confirmed, layout_recovered, layout_death;

    private CardView cardView_indo;
    private static final int[] COLOR_CUSTOM = {
            rgb("#b9aaf7"), rgb("#8aca2b"), rgb("#7d62ef"), rgb("#3498db")
    };
    private static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color) & 0xFF;
        return Color.rgb(r, g, b);
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvPositif = view.findViewById(R.id.txt_confirmed);
        tvSembuh = view.findViewById(R.id.txt_recovered);
        tvMati = view.findViewById(R.id.txt_deaths);
        tvTanggal = view.findViewById(R.id.txt_tanggal);
        tvCaseReported = view.findViewById(R.id.txt_cases);
        layout_case = view.findViewById(R.id.layout_cases);

        tvIdConfirmed = view.findViewById(R.id.txt_confirmed1);
        tvIdRecovered = view.findViewById(R.id.txt_rcv);
        tvIdDeath = view.findViewById(R.id.txt_death);
        tvLocate = view.findViewById(R.id.txt_location);
        view.findViewById(R.id.txt_location);
        tvUpdate = view.findViewById(R.id.txt_update);
        ll_global = view.findViewById(R.id.ll_global);
        ll_global.setOnClickListener(v -> startActivity(new Intent(getContext(), GlobalActivity.class)));

        cardView_indo = view.findViewById(R.id.cardView_indo);
        cardView_indo.setOnClickListener(v -> startActivity(new Intent(getContext(), CaseIndoActivity.class)));

        //layout
        layout_confirmed = view.findViewById(R.id.layout_confirmed);
        layout_confirmed.setOnClickListener(v -> startActivity(new Intent(getContext(), ConfirmedActivity.class)));
        layout_recovered = view.findViewById(R.id.layout_recovered);
        layout_recovered.setOnClickListener(v -> startActivity(new Intent(getContext(), RecoveredActivity.class)));
        layout_death = view.findViewById(R.id.layout_death);
        layout_death.setOnClickListener(v -> startActivity(new Intent(getContext(), DeathActivity.class)));

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");
        progressDialog.show();

        CountryViewModel countryViewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(CountryViewModel.class);
        countryViewModel.setCountryData();
        countryViewModel.getCountryData().observe(getViewLifecycleOwner(), countryModels -> {
            CountryModel countryModel = countryModels.get(0);
            String nama = countryModel.getNamel();
            String confirmed = countryModel.getIdnConfirmed();
            String recovered = countryModel.getIdnRecovered();
            String death = countryModel.getIdnDeaths();

            tvIdConfirmed.setText(confirmed);
            tvIdRecovered.setText(recovered);
            tvIdDeath.setText(death);
            tvLocate.setText(nama);
        });

        PieChart pieChart = view.findViewById(R.id.pie_chart);
        GlobalViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(GlobalViewModel.class);
        viewModel.setGlobalData();
        viewModel.getGlobalData().observe(getViewLifecycleOwner(), globalModel -> {
            progressDialog.dismiss();
            layout_case.setVisibility(View.VISIBLE);
            List<PieEntry> pieEntries = new ArrayList<>();
            pieEntries.add(new PieEntry(globalModel.getGlobalConfirmed().getValue(), getResources().getString(R.string.confirm)));
            pieEntries.add(new PieEntry(globalModel.getGlobalRecovered().getValue(), getResources().getString(R.string.recovery)));
            pieEntries.add(new PieEntry(globalModel.getGlobalDeaths().getValue(), getResources().getString(R.string.death)));

            PieDataSet pieDataSet = new PieDataSet(pieEntries, getResources().getString(R.string.coronavirus_pandemic));
            pieDataSet.setColors(COLOR_CUSTOM);
            //date
            String dateLastUpdate = globalModel.getLastUpdate();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            try {
                date1 = dateFormat.parse(dateLastUpdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
            assert date1 != null;
            String newFormat = format.format(date1);
            Log.d("TAG", "onChanged format: " + newFormat);

            tvTanggal.setText(newFormat);
            tvUpdate.setText("Last update "+newFormat);

            String confirmed = String.valueOf(globalModel.getGlobalConfirmed().getValue());
            String recovered = String.valueOf(globalModel.getGlobalRecovered().getValue());
            String deaths = String.valueOf(globalModel.getGlobalDeaths().getValue());

            int intPositif = Integer.parseInt(confirmed);
            int intSembuh = Integer.parseInt(recovered);
            int intMati = Integer.parseInt(deaths);
            int caseReported = intPositif + intSembuh + intMati;
            Log.d("TAG", "Case Reported: " + caseReported);
            tvCaseReported.setText(decim.format(caseReported));

            tvPositif.setText(decim.format(intPositif));
            tvSembuh.setText(decim.format(intSembuh));
            tvMati.setText(decim.format(intMati));

            Legend legend = pieChart.getLegend();
            legend.setEnabled(false);

            PieData pieData = new PieData(pieDataSet);
            pieData.setDrawValues(false);
            pieChart.setDrawEntryLabels(false);
            pieChart.setDescription(null);
            pieChart.setVisibility(View.VISIBLE);
            pieChart.animateY(1500, Easing.EaseOutSine);
            pieChart.setHoleRadius(75f);
            pieChart.setRotationAngle(130);
            pieChart.setHoleColor(Color.TRANSPARENT);
            pieChart.invalidate();
            pieChart.setData(pieData);
        });
    }
}
