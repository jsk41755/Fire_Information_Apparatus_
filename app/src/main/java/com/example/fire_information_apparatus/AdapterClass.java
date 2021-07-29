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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.Object_Name_txt.setText(list.get(position).getObject_Name());
        holder.By_Place_txt.setText(list.get(position).getBy_Place());
        holder.Old_Address_txt.setText(list.get(position).getOld_Address());
        holder.New_Address_txt.setText(list.get(position).getNew_Address());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Log.d("Object_Manager", list.get(position).getObject_Manager());
                Intent intent = new Intent(v.getContext(), Detail_activity.class);
                intent.putExtra("Object_Name",list.get(position).getObject_Name());
                intent.putExtra("By_Place",list.get(position).getBy_Place());
                intent.putExtra("Old_Address",list.get(position).getOld_Address());
                intent.putExtra("New_Address",list.get(position).getNew_Address());
                intent.putExtra("Object_Manager",list.get(position).getObject_Manager());
                intent.putExtra("Manager_General_Telephone",list.get(position).getManager_General_Telephone());
                intent.putExtra("Manager_Cell_Phone",list.get(position).getManager_Cell_Phone());
                intent.putExtra("Num",list.get(position).getNum());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Object_Name_txt, By_Place_txt, Old_Address_txt, New_Address_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Object_Name_txt = itemView.findViewById(R.id.Object_Name_txt);
            By_Place_txt =  itemView.findViewById(R.id.By_Place_txt);
            Old_Address_txt = itemView.findViewById(R.id.Old_Address_txt);
            New_Address_txt =  itemView.findViewById(R.id.New_Address_txt);

        }
    }

}
