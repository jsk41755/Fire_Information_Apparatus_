package com.example.fire_information_apparatus;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    ArrayList<Helper> list;
    public AdapterClass(ArrayList<Helper> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.value.setText(list.get(position).getValue());
        holder.value2.setText(list.get(position).getValue2());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Log.d("erge","hfhd");
                Intent intent = new Intent(v.getContext(), Detail_activity.class);
                intent.putExtra("abcd",list.get(position).getValue());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView value, value2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
            value2 =  itemView.findViewById(R.id.value2);
        }
    }

}
