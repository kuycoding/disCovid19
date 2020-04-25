package com.kuycoding.covid19.ui.activities.bantuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.adapter.HotlineAdapter;
import com.kuycoding.covid19.model.HotlineModel;

import java.util.ArrayList;

public class HotlineActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<HotlineModel> list = new ArrayList<>();
    private HotlineAdapter adapter;
    private RelativeLayout relativeLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);

        relativeLayout2 = findViewById(R.id.relativeLayout2);
        relativeLayout2.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.rv_hotline);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list.addAll(getListFisika());
        adapter = new HotlineAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<HotlineModel> getListFisika() {
        String[] dataNama = getResources().getStringArray(R.array.nama_hotline);
        String[] dataNomor = getResources().getStringArray(R.array.nomor_hotline);

        ArrayList<HotlineModel> hotlineModels = new ArrayList<>();
        for (int i = 0; i < dataNama.length; i++) {
            HotlineModel model = new HotlineModel();
            model.setNama(dataNama[i]);
            model.setNoPhone(dataNomor[i]);

            hotlineModels.add(model);
        }
        return hotlineModels;
    }
}
