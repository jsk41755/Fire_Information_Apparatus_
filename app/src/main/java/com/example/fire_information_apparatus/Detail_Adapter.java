package com.example.fire_information_apparatus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Detail_Adapter extends RecyclerView.Adapter<Detail_Adapter.DetailViewHolder> {

    private ArrayList<Detail_Helper> arrayList;
    private Context context;

    public Detail_Adapter(ArrayList<Detail_Helper> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_card_holder,parent,false);
        DetailViewHolder holder = new DetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Detail_Adapter.DetailViewHolder holder, int position) {
        holder.Reporting_Time.setText(arrayList.get(position).getReporting_Time());
        holder.by_Case_Cause.setText(arrayList.get(position).getBy_Case_Cause());
        holder.Jurisdiction_Center.setText(arrayList.get(position).getJurisdiction_Center());
        holder.Reported_Content.setText(arrayList.get(position).getReported_Content());
    }

    @Override
    public int getItemCount() {
        Log.d("ergerrr", String.valueOf(arrayList.size()));
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView Reporting_Time, by_Case_Cause, Jurisdiction_Center, Reported_Content;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            Reporting_Time = itemView.findViewById(R.id.Reporting_Time);
            by_Case_Cause =  itemView.findViewById(R.id.by_Case_Cause);
            Jurisdiction_Center = itemView.findViewById(R.id.Jurisdiction_Center);
            Reported_Content =  itemView.findViewById(R.id.Reported_Content);
        }
    }
}
