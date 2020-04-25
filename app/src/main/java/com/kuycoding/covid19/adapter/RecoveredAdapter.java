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
import com.kuycoding.covid19.model.Recovered;

import java.util.ArrayList;
import java.util.List;

public class RecoveredAdapter extends RecyclerView.Adapter<RecoveredAdapter.ViewHolder> {
    private List<Recovered> recoveredList;
    private Context context;

    public RecoveredAdapter(List<Recovered> recoveredList, Context context) {
        this.recoveredList = recoveredList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecoveredAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recovered, parent, false);
        return new RecoveredAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecoveredAdapter.ViewHolder holder, int position) {
        Recovered recovered = recoveredList.get(position);
        holder.tvInfo.setText("Last update "+recovered.getLastUpdate());
        holder.tvLocate.setText(recovered.getCountryRegion());
        holder.tvRecovered.setText(recovered.getRecovered());
    }

    @Override
    public int getItemCount() {
        return recoveredList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocate, tvInfo, tvRecovered;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLocate = itemView.findViewById(R.id.txt_location);
            tvInfo = itemView.findViewById(R.id.txt_information);
            tvRecovered = itemView.findViewById(R.id.txt_recovered);
        }
    }
}
