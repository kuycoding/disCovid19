package com.kuycoding.covid19.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.model.provinsi.Provinsi;

import java.util.List;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.ViewModel> {
    private List<Provinsi> listProvinsi;
    private Context context;

    public ProvinsiAdapter(List<Provinsi> listProvinsi, Context context) {
        this.listProvinsi = listProvinsi;
        this.context = context;
    }

    @NonNull
    @Override
    public ProvinsiAdapter.ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_global, parent, false);
        return new ViewModel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProvinsiAdapter.ViewModel holder, int position) {
        Provinsi provinsi = listProvinsi.get(position);
        holder.tvLocate.setText(provinsi.getAttributes().getProvinsi());
        holder.tvPositif.setText("Positif : "+provinsi.getAttributes().getKasusPosi());
        holder.tvSembuh.setText("Sembuh : " + Integer.parseInt(provinsi.getAttributes().getKasusSemb()));
        holder.tvMeninggal.setText("Meninggal : " +provinsi.getAttributes().getKasusMeni());
    }

    @Override
    public int getItemCount() {
        return listProvinsi.size();
    }

    class ViewModel extends RecyclerView.ViewHolder {
        private TextView tvLocate, tvPositif, tvSembuh, tvMeninggal, tvUpdate;
        ViewModel(@NonNull View itemView) {
            super(itemView);
            tvLocate = itemView.findViewById(R.id.txt_location);
            tvPositif = itemView.findViewById(R.id.txt_positif);
            tvMeninggal = itemView.findViewById(R.id.txt_death);
            tvSembuh = itemView.findViewById(R.id.txt_rcv);
        }
    }
}
