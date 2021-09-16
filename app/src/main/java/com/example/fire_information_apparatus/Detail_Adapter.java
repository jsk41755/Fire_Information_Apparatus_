package com.example.fire_information_apparatus;

import android.content.Context;
import android.content.Intent;
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
        holder.Reported_Content.setText(arrayList.get(position).getReported_Content());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext(), Detail_Edit_Activity.class);

                intent.putExtra("Position",String.valueOf(position+1));
                intent.putExtra("Reporting_Time",arrayList.get(position).getReporting_Time());
                intent.putExtra("Reported_Content",arrayList.get(position).getReported_Content());

                intent.putExtra("Factors_Stack",arrayList.get(position).getFactors_Stack());
                intent.putExtra("Factors_Position",arrayList.get(position).getFactors_Position());
                intent.putExtra("Object_Name",arrayList.get(position).getObject_Name());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView Reporting_Time, by_Case_Cause, Jurisdiction_Center, Reported_Content;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            Reporting_Time = itemView.findViewById(R.id.Reporting_Time);
            by_Case_Cause =  itemView.findViewById(R.id.by_Case_Cause);
            Reported_Content =  itemView.findViewById(R.id.Reported_Content);
        }
    }
}
