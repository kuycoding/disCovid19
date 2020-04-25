package com.kuycoding.covid19.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.model.HotlineModel;

import java.util.ArrayList;

public class HotlineAdapter extends RecyclerView.Adapter<HotlineAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HotlineModel> listHotline;

    public HotlineAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<HotlineModel> getListHotline() {
        return listHotline;
    }

    public HotlineAdapter(ArrayList<HotlineModel> listHotline) {
        this.listHotline = listHotline;
    }

    @NonNull
    @Override
    public HotlineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotline, parent, false);
        return new HotlineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotlineAdapter.ViewHolder holder, int position) {
        HotlineModel hotlineModel = listHotline.get(position);
        holder.nama.setText(hotlineModel.getNama());
        holder.nomor.setText(hotlineModel.getNoPhone());
    }

    @Override
    public int getItemCount() {
        return listHotline.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, nomor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txt_nama);
            nomor = itemView.findViewById(R.id.txt_nomor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    context = v.getContext();
                    HotlineModel hotlineModel = listHotline.get(position);
                    Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + hotlineModel.getNoPhone()));
                    if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(context, "permission not granted", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions((Activity) v.getContext(),
                                new String[]{Manifest.permission.CALL_PHONE},143);
                    }else{
                        context.startActivity(call);
                    }
                    Log.d("TAG", "onClick: "+hotlineModel.getNoPhone());
                }
            });
        }
    }
}
