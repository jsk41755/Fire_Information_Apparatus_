package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.ContentValues.TAG;

public class Detail_activity extends AppCompatActivity {

    private TextView Object_Name, By_Place, Old_Address, New_Address, Jurisdiction_Center, Reporting_Time, Object_Manager, Manager_General_Telephone,
            Manager_Cell_Phone, by_Case_Cause;
    private Button editbtn, addbtn;

    private ImageButton General_Call_btn, Cell_Call_btn;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Detail_Helper> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference, refdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.Detail_RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        Object_Name = findViewById(R.id.Object_Name);
        Old_Address = findViewById(R.id.Old_Address);
        New_Address = findViewById(R.id.New_Address);
        Object_Manager = findViewById(R.id.Object_Manager);
        By_Place = findViewById(R.id.By_Place);
        Manager_General_Telephone = findViewById(R.id.Manager_General_Telephone);
        Manager_Cell_Phone = findViewById(R.id.Manager_Cell_Phone);

        General_Call_btn = findViewById(R.id.General_Call_btn);
        Cell_Call_btn = findViewById(R.id.Cell_Call_btn);

        Object_Name = findViewById(R.id.Object_Name);
        Old_Address = findViewById(R.id.Old_Address);

        editbtn = findViewById(R.id.button234);
        addbtn = findViewById(R.id.button23);


        Intent intent = getIntent();

        Object_Name.setText(intent.getStringExtra("Object_Name"));
        Old_Address.setText(intent.getStringExtra("Old_Address"));
        New_Address.setText(intent.getStringExtra("New_Address"));
        Object_Manager.setText(intent.getStringExtra("Object_Manager"));
        Manager_General_Telephone.setText(intent.getStringExtra("Manager_General_Telephone"));
        Manager_Cell_Phone.setText(intent.getStringExtra("Manager_Cell_Phone"));
        By_Place.setText(intent.getStringExtra("By_Place"));



        database = FirebaseDatabase.getInstance();
        refdetail = database.getReference("Data").child(intent.getStringExtra("Object_Name"));
        databaseReference = database.getReference("Data").child(intent.getStringExtra("Object_Name")).child("Detail_Card");
        Log.d("error", intent.getStringExtra("Object_Name"));


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot detail_snapshot : snapshot.getChildren()){
                    Detail_Helper detail_helper = detail_snapshot.getValue(Detail_Helper.class);
                    arrayList.add(detail_helper);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Detail_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new Detail_Adapter(arrayList, this);
        recyclerView.setAdapter(adapter);



        /*String uKey = getIntent().getStringExtra("uKey");

        DataRef = FirebaseDatabase.getInstance().getReference().child("Data").child(uKey);*/

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent_edit = new Intent(v.getContext(), Edit_Child_Activity.class);
                intent_edit.putExtra("Edit_Object_Name", intent.getStringExtra("Object_Name"));
                intent_edit.putExtra("Edit_Old_Address", intent.getStringExtra("Old_Address"));
                intent_edit.putExtra("Edit_New_Address", intent.getStringExtra("New_Address"));
                intent_edit.putExtra("Edit_Object_Manager", intent.getStringExtra("Object_Manager"));
                intent_edit.putExtra("Edit_Manager_General_Telephone", intent.getStringExtra("Manager_General_Telephone"));
                intent_edit.putExtra("Edit_Manager_Cell_Phone", intent.getStringExtra("Manager_Cell_Phone"));
                context.startActivity(intent_edit);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context add_context = v.getContext();

                Intent intent_add = new Intent(v.getContext(), Add_Object_Activity.class);
                intent_add.putExtra("Add_Object_Name", intent.getStringExtra("Object_Name"));
                intent_add.putExtra("Num", intent.getStringExtra("Num"));
                add_context.startActivity(intent_add);
            }
        });

        General_Call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String General_phone = Manager_General_Telephone.getText().toString();

                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + General_phone));
/*
Intent i = new Intent(Intent.ACTION_DIAL);
i.setData(Uri.parse("tel:0612312312"));
if (i.resolveActivity(getPackageManager()) != null) {
      startActivity(i);
}*/
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }

            }
        });

        Cell_Call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cell_phone = Manager_Cell_Phone.getText().toString();

                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + Cell_phone));

                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();

        Intent intent = getIntent();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Data").child(intent.getStringExtra("Object_Name")).child("Detail_Card");
        Log.d("error", intent.getStringExtra("Object_Name"));

        refdetail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Old_Address.setText(String.valueOf(snapshot.child("Old_Address").getValue()));
                New_Address.setText(String.valueOf(snapshot.child("New_Address").getValue()));
                Object_Manager.setText(String.valueOf(snapshot.child("Object_Manager").getValue()));
                Manager_General_Telephone.setText(String.valueOf(snapshot.child("Manager_General_Telephone").getValue()));
                Manager_Cell_Phone.setText(String.valueOf(snapshot.child("Manager_Cell_Phone").getValue()));
                By_Place.setText(String.valueOf(snapshot.child("By_Place").getValue()));
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot detail_snapshot : snapshot.getChildren()){
                    Detail_Helper detail_helper = detail_snapshot.getValue(Detail_Helper.class);
                    arrayList.add(detail_helper);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Detail_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new Detail_Adapter(arrayList, this);
        recyclerView.setAdapter(adapter);

        Log.d(TAG,"onRestart" + intent.getStringExtra("Old_Address"));
    }

}