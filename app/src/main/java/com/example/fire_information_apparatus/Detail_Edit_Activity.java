package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ServerValues;

import java.util.ArrayList;

public class Detail_Edit_Activity extends AppCompatActivity {

    private EditText Add_Reporting_Time, Add_Reported_Content;
    private Button Edit_btn, Close_btn;

    String key_string, On_Place;

    String Add_Reporting_Time_TXT, Add_Reported_Content_TXT;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, defstat, databaseReference_Place;

    ArrayAdapter<String> arrayAdapter_child;

    ArrayList<String> Artificial_Factors,Administrative_Factors,System_Factors,Etc_Factors; //인위적요인, 관리적요인, 시스템적요인, 기타

    String[] by_Case_Cause = {"인위적요인", "관리적요인", "시스템요인","기타"};

    ArrayList<String> by_Case_Cause_Select = new ArrayList<>(); // 장소 1차 선택
    String By_Case_Cause_cv;

    String Case_Stack, Con_Case_Stack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_detail_edit);

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

        Add_Reporting_Time = findViewById(R.id.Edit_Reporting_Time);
        Add_Reporting_Time.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        Add_Reported_Content = findViewById(R.id.Edit_Reported_Content);
        Edit_btn = findViewById(R.id.edit_button);
        Close_btn = findViewById(R.id.exit_button);

        Spinner parent = findViewById(R.id.Edit_By_Case_Cause_1);
        Spinner child = findViewById(R.id.Edit_By_Case_Cause_2);

        Intent intent2 = getIntent();
        Add_Reporting_Time_TXT = intent2.getStringExtra("Reporting_Time");
        Add_Reported_Content_TXT = intent2.getStringExtra("Reported_Content");
        key_string = intent2.getStringExtra("Position");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Data").child(intent2.getStringExtra("Object_Name")).child("Detail_Card");
        databaseReference_Place = firebaseDatabase.getReference().child("Data").child(intent2.getStringExtra("Object_Name"));
        defstat = firebaseDatabase.getReference().child("Statistics");


        Log.d("Add_Reporting_Time_TXT", Add_Reporting_Time_TXT.substring(0,4));
        Log.d("Add_Reporting_Time_TXT", Add_Reporting_Time_TXT.substring(6,8));

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

        Edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAdd_Reporting_Time = Add_Reporting_Time.getText().toString().trim();
                String sAdd_Reported_Content = Add_Reported_Content.getText().toString().trim();

                if(intent2.getStringExtra("Object_Name") != null && sAdd_Reporting_Time.length() == 12) {

                    defstat.child("Case_Stack").child(intent2.getStringExtra("Factors_Stack")).child(intent2.getStringExtra("Factors_Position")).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!(snapshot.getValue().equals(""))) {
                                int value = (int) snapshot.getValue(Integer.class);//저장된 값을 숫자로 받아오고
                                value -= 1;//숫자를 1 감소시키고,
                                defstat.child("Case_Stack").child(intent2.getStringExtra("Factors_Stack")).child(intent2.getStringExtra("Factors_Position")).setValue(value);//저장
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                    String sReporting_Time_Set = sAdd_Reporting_Time.substring(0,4) + "년 " + sAdd_Reporting_Time.substring(4,6) + "월 " + sAdd_Reporting_Time.substring(6,8) + "일 " +
                            sAdd_Reporting_Time.substring(8,10) + "시" + sAdd_Reporting_Time.substring(10,12) + "분";

                    databaseReference.child(key_string).child("Reporting_Time").setValue(sReporting_Time_Set);
                    databaseReference.child(key_string).child("By_Case_Cause").setValue(By_Case_Cause_cv);
                    databaseReference.child(key_string).child("Reported_Content").setValue(sAdd_Reported_Content);

                    databaseReference.child(key_string).child("Factors_Stack").setValue(Case_Stack);
                    databaseReference.child(key_string).child("Factors_Position").setValue(Con_Case_Stack);

                    databaseReference_Place.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(String.valueOf(snapshot.child("By_Place").getValue()).equals("공장/창고")){
                                On_Place = "Factory_Place";
                            }
                            else if(String.valueOf(snapshot.child("By_Place").getValue()).equals("주거")){
                                On_Place = "Dwelling_Place";
                            }
                            else if(String.valueOf(snapshot.child("By_Place").getValue()).equals("노유자")){
                                On_Place = "Senior_Place";
                            }
                            else if(String.valueOf(snapshot.child("By_Place").getValue()).equals("기타")){
                                On_Place = "Etc_Place";
                            }
                            else
                                On_Place = "";

                            defstat.child("By_Place").child(On_Place).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int value = (int) snapshot.child(Add_Reporting_Time_TXT.substring(0,4)).child(Add_Reporting_Time_TXT.substring(6,8)).getValue(Integer.class);//저장된 값을 숫자로 받아오고
                                        value -= 1;//숫자를 1 감소시켜서
                                        defstat.child("By_Place").child(On_Place).child(Add_Reporting_Time_TXT.substring(0,4)).child(Add_Reporting_Time_TXT.substring(6,8)).setValue(value);//저장
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });

                            defstat.child("By_Place").child(On_Place).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.child(sAdd_Reporting_Time.substring(0,4)).child(sAdd_Reporting_Time.substring(4,6)).getValue() != null) {
                                        int value = (int) snapshot.child(sAdd_Reporting_Time.substring(0,4)).child(sAdd_Reporting_Time.substring(4,6)).getValue(Integer.class);//저장된 값을 숫자로 받아오고
                                        value += 1;//숫자를 1 증가시켜서
                                        defstat.child("By_Place").child(On_Place).child(sAdd_Reporting_Time.substring(0,4)).child(sAdd_Reporting_Time.substring(4,6)).setValue(value);//저장
                                    }
                                    else{
                                        defstat.child("By_Place").child(On_Place).child(sAdd_Reporting_Time.substring(0,4)).child(sAdd_Reporting_Time.substring(4,6)).setValue(1);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    defstat.child("Case_Stack").child(Case_Stack).child(Con_Case_Stack).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int value = (int)snapshot.getValue(Integer.class);//저장된 값을 숫자로 받아오고
                            value +=1;//숫자를 1 증가시켜서
                            defstat.child("Case_Stack").child(Case_Stack).child(Con_Case_Stack).setValue(value);//저장
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"출동시각을 정확히 입력하지 않으셨습니다.\n ex)202108301220",Toast.LENGTH_LONG).show();
                }

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