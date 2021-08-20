package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity {

    private AdapterClass adapter;

    DatabaseReference ref;
    ArrayList<Helper> list;
    RecyclerView recyclerView;
    SearchView searchView;
    Button addBtn, staticBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        ref = FirebaseDatabase.getInstance().getReference().child("Data");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchview);
        addBtn = findViewById(R.id.addBtn);
        staticBtn = findViewById(R.id.StaticBtn);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @NonNull
                @Override
                protected Object clone() throws CloneNotSupportedException {
                    return super.clone();
                }

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        list = new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()){
                            list.add(ds.getValue(Helper.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(MainActivity.this, Add_ListView.class);
                startActivity(homeIntent);
            }
        });

        staticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staticIntent = new Intent(MainActivity.this, Statics.class);
                startActivity(staticIntent);
            }
        });

    }

    private void search(String str) {
        if (!str.equals("")){
            ArrayList<Helper> myList = new ArrayList<>();
            for (Helper object : list){
                if(object.getObject_Name().toLowerCase().contains(str.toLowerCase())){
                    myList.add(object);
                }
            }
            AdapterClass adapterClass = new AdapterClass(myList);
            recyclerView.setAdapter(adapterClass);
        }

    }
}