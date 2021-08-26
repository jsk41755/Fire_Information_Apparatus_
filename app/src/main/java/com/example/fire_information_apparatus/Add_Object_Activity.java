package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class Add_Object_Activity extends AppCompatActivity {

    private TextView Object_Name_txt;
    private EditText Add_Reporting_Time, Add_Reported_Content;
    private Button Save_btn, Close_btn;

    int key = 0;
    int num = 0;
    String key_string;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, defstat;

    ArrayAdapter<String> arrayAdapter_child;

    ArrayList<String> Artificial_Factors,Administrative_Factors,System_Factors,Etc_Factors; //인위적요인, 관리적요인, 시스템적요인, 기타

    String[] by_Case_Cause = {"인위적요인", "관리적요인", "시스템요인","기타"};

    ArrayList<String> by_Case_Cause_Select = new ArrayList<>(); // 장소 1차 선택
    String By_Case_Cause_cv;

    String Case_Stack, Con_Case_Stack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);

        Artificial_Factors = new ArrayList<>();
        Artificial_Factors.add("먼지/연기/수증기");
        Artificial_Factors.add("조리/화기");
        Artificial_Factors.add("점검/공사중");
        Artificial_Factors.add("냉/난방기");
        Artificial_Factors.add("기타");

        Administrative_Factors = new ArrayList<>();
        Administrative_Factors.add("습기");
        Administrative_Factors.add("관리불량");
        Administrative_Factors.add("기타");

        System_Factors = new ArrayList<>();
        System_Factors.add("노후");
        System_Factors.add("시공");
        System_Factors.add("기기오류");
        System_Factors.add("기타");

        Etc_Factors = new ArrayList<>();
        Etc_Factors.add("복구/원인불명");

        Object_Name_txt = findViewById(R.id.Object_Name);
        Add_Reporting_Time = findViewById(R.id.Add_Reporting_Time);
        Add_Reported_Content = findViewById(R.id.Add_Reported_Content);
        Save_btn = findViewById(R.id.Save_btn);
        Close_btn = findViewById(R.id.Close_btn);


        Spinner parent = findViewById(R.id.Add_By_Case_Cause_1);
        Spinner child = findViewById(R.id.Add_By_Case_Cause_2);

        Intent intent = getIntent();
        String Object_Name = intent.getStringExtra("Add_Object_Name");
        String Num = intent.getStringExtra("Num");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Data").child(Object_Name).child("Detail_Card");
        defstat = firebaseDatabase.getReference().child("Statistics");

        Object_Name_txt.setText(Object_Name);

        Log.d("add_object",Num);
        key = Integer.parseInt(Num);
        Log.d("add_object12",Integer.toString(key));

        for( num = 0; num <= key; num++) {

        }
        Log.d("add_object12",Integer.toString(num));

        key_string = Integer.toString(num);
        Log.d("add_object",key_string);



        ArrayAdapter<String> by_Case_Cause_adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, by_Case_Cause
        );

        by_Case_Cause_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        parent.setAdapter(by_Case_Cause_adapter);

        parent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Artificial_Factors);
                    by_Case_Cause_Select = Artificial_Factors;
                    Case_Stack = "Artificial_Factors";
                }

                if(position == 1){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Administrative_Factors);
                    by_Case_Cause_Select = Administrative_Factors;
                    Case_Stack = "Administrative_Factors";
                }

                if(position == 2){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, System_Factors);
                    by_Case_Cause_Select = System_Factors;
                    Case_Stack = "System_Factors";
                }

                if(position == 3){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Etc_Factors);
                    by_Case_Cause_Select = Etc_Factors;
                    Case_Stack = "Etc_Factors";
                }

                child.setAdapter(arrayAdapter_child);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                By_Case_Cause_cv = by_Case_Cause_Select.get(position);
                Con_Case_Stack = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAdd_Reporting_Time = Add_Reporting_Time.getText().toString().trim();
                String sAdd_Reported_Content = Add_Reported_Content.getText().toString().trim();

                firebaseDatabase.getReference().child("Data").child(Object_Name).child("Num").setValue(key_string);


                if(Object_Name != null) {
                    databaseReference.child(key_string).child("Reporting_Time").setValue(sAdd_Reporting_Time);
                    databaseReference.child(key_string).child("By_Case_Cause").setValue(By_Case_Cause_cv);
                    databaseReference.child(key_string).child("Reported_Content").setValue(sAdd_Reported_Content);

                    databaseReference.child(key_string).child("Factors_Stack").setValue(Case_Stack);
                    databaseReference.child(key_string).child("Factors_Position").setValue(Con_Case_Stack);
                    databaseReference.child(key_string).child("Object_Name").setValue(Object_Name);//관할센터


                    defstat.child("Case_Stack").child(Case_Stack).child(Con_Case_Stack).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int value = (int)snapshot.getValue(Integer.class);//저장된 값을 숫자로 받아오고
                            value +=1;//숫자를 1 증가시켜서
                            // Log.d("same", Jurisdiction_Center_Stack);
                            defstat.child("Case_Stack").child(Case_Stack).child(Con_Case_Stack).setValue(value);//저장
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //Log.e("MainActivity", String.valueOf(databaseError.toException()));
                        }
                    });
                }
                finish();
            }
        });

        Close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}