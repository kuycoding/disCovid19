package com.kuycoding.covid19.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.model.Death;

import java.util.ArrayList;
import java.util.List;

public class DeathAdapter extends RecyclerView.Adapter<DeathAdapter.ViewHolder> {
    private Context context;
    private List<Death> list = new ArrayList<>();

    public DeathAdapter(Context context, List<Death> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DeathAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_death, parent, false);
        return new DeathAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DeathAdapter.ViewHolder holder, int position) {
        Death death = list.get(position);
        holder.tvLocate.setText(death.getCountryRegion());
        holder.tvInfo.setText("Last update " + death.getLastUpdate());
        holder.tvDeath.setText(death.getDeaths());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocate, tvInfo, tvDeath;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLocate = itemView.findViewById(R.id.txt_location);
            tvInfo = itemView.findViewById(R.id.txt_information);
            tvDeath = itemView.findViewById(R.id.txt_death);
        }
    }
}
