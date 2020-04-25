package com.kuycoding.covid19.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.model.Confirmed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfirmedAdapter extends RecyclerView.Adapter<ConfirmedAdapter.ViewHolder> {
    private List<Confirmed> list;

    public ConfirmedAdapter(List<Confirmed> list) {
        this.list = list;
    }

    public List<Confirmed> getList() {
        return list;
    }

    public void setList(ArrayList<Confirmed> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ConfirmedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_confirmed, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ConfirmedAdapter.ViewHolder holder, int position) {
        Confirmed confirmed = list.get(position);
        holder.tvRecovered.setText(confirmed.getConfirmed());
        holder.tvLocate.setText(confirmed.getCountryRegion());
        String dates = confirmed.getLastUpdate();
        holder.tvInfo.setText("Last update "+ dates);
    }

    private String converTime(String dates) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String converDate = simpleDateFormat1.format(date);
        return converDate;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocate, tvRecovered, tvInfo;
        private String newFormat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLocate = itemView.findViewById(R.id.txt_location);
            tvRecovered = itemView.findViewById(R.id.txt_positif);
            tvInfo = itemView.findViewById(R.id.txt_information);
        }
    }
}
