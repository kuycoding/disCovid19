package com.kuycoding.covid19.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.model.HospitalModel;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private ArrayList<HospitalModel> listHospital;

    public HospitalAdapter(ArrayList<HospitalModel> listHospital) {
        this.listHospital = listHospital;
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        return new HospitalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {
        HospitalModel model = listHospital.get(position);
        holder.nama.setText(model.getNamaRs());
        holder.alamat.setText(model.getAlamatRs());
    }

    @Override
    public int getItemCount() {
        return listHospital.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, alamat, maps, call;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txt_hospital);
            alamat = itemView.findViewById(R.id.txt_alamat);
            maps = itemView.findViewById(R.id.txt_maps);
            maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Context context = v.getContext();
                    HospitalModel model = listHospital.get(position);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getRoute()));
                    context.startActivity(intent);
                }
            });

            call = itemView.findViewById(R.id.txt_call);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Context context = v.getContext();
                    HospitalModel model = listHospital.get(position);

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + model.getTelp()));
                    if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(context, "permission not granted", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions((Activity) v.getContext(),
                                new String[]{Manifest.permission.CALL_PHONE},143);
                    }else{
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
