package com.example.fire_information_apparatus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Add_ListView extends AppCompatActivity {

    private EditText Object_Name, Old_Address, New_Address, Reporting_Time, Object_Manager, Manager_General_Telephone, Manager_Cell_Phone, Reported_Content;
    private Button Edit_btn, Close_btn;

    ArrayAdapter<String> arrayAdapter_child;

    ArrayList<String> Artificial_Factors,Administrative_Factors,System_Factors,Etc_Factors; //인위적요인, 관리적요인, 시스템적요인, 기타

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String[] items = {"영운119안전센터", "오창안전센터", "내수안전센터","율량안전센터","북문안전센터","문의안전센터"};
    String[] items2 = {"공장", "주거", "노유자","기타"};
    String[] by_Case_Cause = {"인위적요인", "관리적요인", "시스템요인","기타"};
    String Jurisdiction_Center_Select;    //관할 선택
    String By_Place_Select; // 장소 선택

    ArrayList<String> by_Case_Cause_Select = new ArrayList<>(); // 장소 1차 선택택
    String By_Case_Cause_cv;    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_information);

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

        Object_Name = findViewById(R.id.Object_Name);
        Old_Address = findViewById(R.id.Old_Address);
        New_Address = findViewById(R.id.New_Address);
        Reporting_Time = findViewById(R.id.Reporting_Time);
        Object_Manager = findViewById(R.id.Object_Manager);
        Manager_General_Telephone = findViewById(R.id.Manager_General_Telephone);
        Manager_Cell_Phone = findViewById(R.id.Manager_Cell_Phone);
        Reported_Content= findViewById(R.id.Reported_Content);

        Edit_btn = findViewById(R.id.edit_button);

        Close_btn = findViewById(R.id.close_button);


        Spinner Jurisdiction_Center = findViewById(R.id.Jurisdiction_Center);
        Spinner et_value5 = findViewById(R.id.et_value5);

        Spinner parent = findViewById(R.id.et_value11);
        Spinner child = findViewById(R.id.et_value12);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Data");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items
        );

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2
        );

        ArrayAdapter<String> by_Case_Cause_adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, by_Case_Cause
        );


        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        by_Case_Cause_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Jurisdiction_Center.setAdapter(adapter1);
        et_value5.setAdapter(adapter2);

        parent.setAdapter(by_Case_Cause_adapter);

        Jurisdiction_Center.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Jurisdiction_Center_Select = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        et_value5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                By_Place_Select = items2[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        parent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Artificial_Factors);
                    by_Case_Cause_Select = Artificial_Factors;
                }

                if(position == 1){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Administrative_Factors);
                    by_Case_Cause_Select = Administrative_Factors;
                }

                if(position == 2){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, System_Factors);
                    by_Case_Cause_Select = System_Factors;
                }

                if(position == 3){
                    arrayAdapter_child = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Etc_Factors);
                    by_Case_Cause_Select = Etc_Factors;
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sObject_Name = Object_Name.getText().toString().trim();
                String sOld_Address = Old_Address.getText().toString().trim();
                String sNew_Address = New_Address.getText().toString().trim();
                String sReporting_Time = Reporting_Time.getText().toString().trim();
                String sObject_Manager = Object_Manager.getText().toString().trim();
                String sManager_General_Telephone = Manager_General_Telephone.getText().toString().trim();
                String sManager_Cell_Phone = Manager_Cell_Phone.getText().toString().trim();
                String sReported_Content = Reported_Content.getText().toString().trim();


                String sKey = sObject_Name;
                String num = "Detail_Card";

                if(sKey != null){
                    databaseReference.child(sKey).child("Object_Name").setValue(sObject_Name);
                    databaseReference.child(sKey).child("Old_Address").setValue(sOld_Address);
                    databaseReference.child(sKey).child("New_Address").setValue(sNew_Address);
                    databaseReference.child(sKey).child("Object_Manager").setValue(sObject_Manager);
                    databaseReference.child(sKey).child("Manager_General_Telephone").setValue(sManager_General_Telephone);
                    databaseReference.child(sKey).child("Manager_Cell_Phone").setValue(sManager_Cell_Phone);
                    databaseReference.child(sKey).child("By_Place").setValue(By_Place_Select);

                    databaseReference.child(sKey).child(num).child("By_Case_Cause").setValue(By_Case_Cause_cv); //사례원인별
                    databaseReference.child(sKey).child(num).child("Jurisdiction_Center").setValue(Jurisdiction_Center_Select);//관할센터
                    databaseReference.child(sKey).child(num).child("Reported_Content").setValue(sReported_Content); //조치내용
                    databaseReference.child(sKey).child(num).child("Reporting_Time").setValue(sReporting_Time); //신고시각

                    Object_Name.setText("");
                    Old_Address.setText("");
                    New_Address.setText("");
                    Reporting_Time.setText("");
                    Object_Manager.setText("");
                    Manager_General_Telephone.setText("");
                    Manager_Cell_Phone.setText("");
                    Reported_Content.setText("");
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
