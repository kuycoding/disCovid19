package com.kuycoding.covid19.ui.activities;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kuycoding.covid19.R;
import com.kuycoding.covid19.adapter.GlobalAdapter;
import com.kuycoding.covid19.model.GlobalDataModel;
import com.kuycoding.covid19.viewModel.GlobalAllDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class GlobalActivity extends AppCompatActivity implements OnMapReadyCallback {
    private List<GlobalDataModel> globalDataModelList = new ArrayList<>();
    private GlobalAdapter adapter;
    private ProgressBar progressBar;
    private GoogleMap googleMap;

    private LinearLayout layout_map;
    private RelativeLayout layout_searh;
    private EditText txt_search;
    private ImageView img_drag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        img_drag = findViewById(R.id.img_drag);
        layout_map = findViewById(R.id.layout_map);
        layout_searh = findViewById(R.id.relativeLayout3);

        txt_search = findViewById(R.id.txt_search);
        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.rv_global);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new GlobalAdapter(globalDataModelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        FloatingActionButton fab_back = findViewById(R.id.fab_back);
        fab_back.setOnClickListener(v -> finish());

        searchView();
        addDataToList();
        maps();
    }

    private void searchView() {
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addDataToList() {
        GlobalAllDataViewModel viewModel = ViewModelProviders.of(this).get(GlobalAllDataViewModel.class);
        viewModel.setGlobalData();
        viewModel.getGlobalData().observe(this, globals -> {
            if (globals != null) {
                globalDataModelList.clear();
                globalDataModelList.addAll(globals);
                adapter.notifyDataSetChanged();
                globalDataModelList = globals;
                if (googleMap != null) {
                    setMarker();
                }
            }
        });
        viewModel.getIsLoad().observe(this, aBoolean -> {
            if (aBoolean != null) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                    layout_searh.setVisibility(View.VISIBLE);
                    layout_map.setVisibility(View.VISIBLE);
                    img_drag.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setMarker() {
        for (int i = 0;i < globalDataModelList.size(); i++) {
            GlobalDataModel globalDataModel = globalDataModelList.get(i);
            String countryName = globalDataModel.getCountryRegion();
            double lat = globalDataModel.getLat();
            double lng = globalDataModel.getLng();

            Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(countryName)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_deaths_marker))
            );
            marker.setTag(i);
        }
    }

    private void maps() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap mGoogleMap) {
        googleMap = mGoogleMap;
        try {
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
            if (!success) {
                Log.e("TAG", "onMapReady: failed parsing style");
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            Log.d("TAG", "onMapReady: " + e.getMessage());
        }
        googleMap.setOnMarkerClickListener(marker -> {
            int position = (int) marker.getTag();
            final GlobalDataModel globalDataModel = globalDataModelList.get(position);
            showDetailMaps(globalDataModel);
            return false;
        });
    }

    private void showDetailMaps(GlobalDataModel globalDataModel) {
        LayoutInflater layoutInflater;
        layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.item_detail_map, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        TextView tvLocate = view.findViewById(R.id.txt_location);
        TextView tvPositif = view.findViewById(R.id.txt_positif);
        TextView tvSembuh = view.findViewById(R.id.txt_sembuh);
        TextView tvMeninggal = view.findViewById(R.id.txt_meninggal);

        tvLocate.setText(globalDataModel.getCountryRegion());
        tvPositif.setText(globalDataModel.getConfirmed());
        tvSembuh.setText(globalDataModel.getRecovered());
        tvMeninggal.setText(globalDataModel.getDeaths());

        dialog.setView(view);
        dialog.setCancelable(true);
        dialog.setPositiveButton("Close", (dialog1, which) -> dialog1.dismiss());
        dialog.show();
    }
}
