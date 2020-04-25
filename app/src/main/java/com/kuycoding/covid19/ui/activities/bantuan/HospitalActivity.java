package com.kuycoding.covid19.ui.activities.bantuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.adapter.HospitalAdapter;
import com.kuycoding.covid19.model.HospitalModel;

import java.util.ArrayList;

public class HospitalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HospitalAdapter adapter;
    private ArrayList<HospitalModel> listHospital = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        recyclerView = findViewById(R.id.rv_hospital);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listHospital.addAll(getListHospital());
        adapter = new HospitalAdapter(listHospital);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<HospitalModel> getListHospital() {
        String[] dataNama = getResources().getStringArray(R.array.rs_nama);
        String[] dataAlamat = getResources().getStringArray(R.array.rs_alamat);
        String[] dataMaps = getResources().getStringArray(R.array.rs_maps);
        String[] dataCall = getResources().getStringArray(R.array.rs_call);

        ArrayList<HospitalModel> models = new ArrayList<>();
        for (int i = 0; i < dataNama.length; i++) {
            HospitalModel hospitalModel = new HospitalModel();
            hospitalModel.setNamaRs(dataNama[i]);
            hospitalModel.setAlamatRs(dataAlamat[i]);
            hospitalModel.setRoute(dataMaps[i]);
            hospitalModel.setTelp(dataCall[i]);

            models.add(hospitalModel);
        }
        return models;
    }
}
