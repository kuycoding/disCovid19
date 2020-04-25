package com.kuycoding.covid19.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.model.GlobalDataModel;

import java.util.ArrayList;
import java.util.List;

public class GlobalAdapter extends RecyclerView.Adapter<GlobalAdapter.ViewHolder> implements Filterable {
    private List<GlobalDataModel> list;
    private List<GlobalDataModel> modelList;

    public GlobalAdapter(List<GlobalDataModel> list) {
        this.list = list;
    }

    public List<GlobalDataModel> getList() {
        return list;
    }

    public void setList(List<GlobalDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GlobalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_global, parent, false);
        return new GlobalAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GlobalAdapter.ViewHolder holder, int position) {
        GlobalDataModel globalDataModel = list.get(position);
        holder.tvRecovered.setText("Sembuh " + globalDataModel.getRecovered());
        holder.tvConfirmed.setText("Positif " + globalDataModel.getConfirmed());
        holder.tvDeath.setText("Meninggal " + globalDataModel.getDeaths());
        String prov = globalDataModel.getProvinceState();
        if (prov == null) {
            holder.tvLocate.setText(globalDataModel.getCountryRegion());
        } else {
            holder.tvLocate.setText(globalDataModel.getCountryRegion()+ ", " + globalDataModel.getProvinceState());
        }

        String dates = globalDataModel.getLastUpdate();
        holder.tvInfo.setText("Last update " + dates);
    }


    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                GlobalDataModel dataModel = new GlobalDataModel();
                final FilterResults filterResults = new FilterResults();

                if (charSequence.length() == 0) {
                    list = modelList;
                } else {
                    final String pattern = charSequence.toString().toLowerCase().trim();
                    for (GlobalDataModel globalDataModel : list) {
                        if (globalDataModel.getCountryRegion().startsWith(pattern)) {
                            modelList.add(globalDataModel);
                        }
                    }
                }

                /*if (charString.isEmpty()) {
                    list = modelList;
                } else {
                    List<GlobalDataModel> resultData = new ArrayList<>();
                    for (GlobalDataModel globalDataModel : modelList) {
                        if (globalDataModel.getCountryRegion().toLowerCase().contains(charString)) {
                            resultData.add(globalDataModel);
                        }
                    }
                    list = resultData;
                    resultData.size();
                }*/
                filterResults.count = list == null ? 0 : list.size();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<GlobalDataModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRecovered, tvConfirmed, tvDeath, tvLocate, tvInfo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeath = itemView.findViewById(R.id.txt_death);
            tvRecovered = itemView.findViewById(R.id.txt_rcv);
            tvConfirmed = itemView.findViewById(R.id.txt_positif);
            tvInfo = itemView.findViewById(R.id.txt_information);
            tvLocate = itemView.findViewById(R.id.txt_location);
        }
    }
}
