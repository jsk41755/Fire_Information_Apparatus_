package com.example.fire_information_apparatus;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add_ListView extends AppCompatActivity {

    private EditText Object_Name, Old_Address, New_Address, Reporting_Time, Object_Manager, Manager_General_Telephone, Manager_Cell_Phone, Reported_Content, Declaration_Number_Phone;
    private Button Edit_btn, Close_btn;

    ArrayAdapter<String> arrayAdapter_child;

    ArrayList<String> Artificial_Factors,Administrative_Factors,System_Factors,Etc_Factors; //인위적요인, 관리적요인, 시스템적요인, 기타

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, defstat;

    String[] items = {"영운", "오창", "내수","율량","북문","문의"};
    String[] items2 = {"공장/창고", "주거", "노유자","기타"};
    String[] by_Case_Cause = {"인위적요인", "관리적요인", "시스템요인","기타"};
    String Jurisdiction_Center_Select;    //관할 선택
    String By_Place_Select; // 장소 선택

    String By_Place;
    String Case_Stack, Con_Case_Stack;

    ArrayList<String> by_Case_Cause_Select = new ArrayList<>(); // 장소 1차 선택
    String By_Case_Cause_cv;    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        Reporting_Time.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        Object_Manager = findViewById(R.id.Object_Manager);
        Manager_General_Telephone = findViewById(R.id.Manager_General_Telephone);
        Manager_General_Telephone.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        Manager_Cell_Phone = findViewById(R.id.Manager_Cell_Phone);
        Manager_Cell_Phone.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        Reported_Content= findViewById(R.id.Reported_Content);
        Declaration_Number_Phone = findViewById(R.id.Declaration_Number_Phone);
        Declaration_Number_Phone.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        Edit_btn = findViewById(R.id.edit_button);

        Close_btn = findViewById(R.id.close_button);


        Spinner Jurisdiction_Center = findViewById(R.id.Jurisdiction_Center);
        Spinner et_value5 = findViewById(R.id.et_value5);

        Spinner parent = findViewById(R.id.et_value11);
        Spinner child = findViewById(R.id.et_value12);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Data");
        defstat = firebaseDatabase.getReference().child("Statistics");

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

                if(position == 0)
                    By_Place = "Factory_Place";
                else if(position == 1)
                    By_Place = "Dwelling_Place";
                else if(position == 2)
                    By_Place = "Senior_Place";
                else if(position == 3)
                    By_Place = "Etc_Place";
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
                Log.d("same5123", Object_Name.getText().toString().trim());
                String sObject_Name = (Object_Name.getText().toString().trim());
                String sOld_Address = Old_Address.getText().toString().trim();
                String sNew_Address = New_Address.getText().toString().trim();
                String sReporting_Time = Reporting_Time.getText().toString().trim();
                String sObject_Manager = Object_Manager.getText().toString().trim();
                String sManager_General_Telephone = Manager_General_Telephone.getText().toString().trim();
                String sManager_Cell_Phone = Manager_Cell_Phone.getText().toString().trim();
                String sReported_Content = Reported_Content.getText().toString().trim();
                String sDeclaration_Number_Phone = Declaration_Number_Phone.getText().toString().trim();

                String sKey = sObject_Name.replaceAll(" ", "");
                String Detail_Card = "Detail_Card";

                databaseReference.child(sKey).child("Object_Name").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue(String.class) != null || sReporting_Time.length() != 12 || sKey == "" || !(sManager_General_Telephone.length() == 10 || sManager_General_Telephone.length() == 11) || sManager_Cell_Phone.length() != 11 || !(sDeclaration_Number_Phone.length() == 11 || sDeclaration_Number_Phone.length() == 10)){
                            Toast.makeText(getApplicationContext(),"중복된 이름 또는 \n정보를 충분히 입력하지 않았습니다.\n전화번호가 없을경우 0으로 채우십시오.",Toast.LENGTH_LONG).show();
                            Log.d("sReporting_Time", String.valueOf(dataSnapshot.getValue(String.class) != null));
                            Log.d("sManager_General_Tel", String.valueOf(sKey == ""));
                            Log.d("sDeclaration_Number", String.valueOf((sManager_General_Telephone.length() == 10 || sManager_General_Telephone.length() == 11)));
                            Log.d("sManager_Cell_Phone", String.valueOf((sDeclaration_Number_Phone.length() == 11 || sDeclaration_Number_Phone.length() == 10)));

                        } else {
                            Log.d("same", sObject_Name);
                            if(sKey != null){
                                String sManager_General_Telephone_com;
                                String sDeclaration_Number_Phone_com;

                                if(sManager_General_Telephone.length() == 10){
                                    sManager_General_Telephone_com = sManager_General_Telephone.substring(0,3) + "-" + sManager_General_Telephone.substring(3,6) + "-" + sManager_General_Telephone.substring(6,10);
                                }
                                else{
                                    sManager_General_Telephone_com = sManager_General_Telephone.substring(0,3) + "-" + sManager_General_Telephone.substring(3,7) + "-" + sManager_General_Telephone.substring(7,11);
                                }

                                String sManager_Cell_Phone_com = sManager_Cell_Phone.substring(0,3) + "-" + sManager_Cell_Phone.substring(3,7) + "-" + sManager_Cell_Phone.substring(7,11);

                                if(sDeclaration_Number_Phone.length() == 10){
                                    sDeclaration_Number_Phone_com = sDeclaration_Number_Phone.substring(0,3) + "-" + sDeclaration_Number_Phone.substring(3,6) + "-" + sDeclaration_Number_Phone.substring(6,10);
                                }
                                else{
                                    sDeclaration_Number_Phone_com = sDeclaration_Number_Phone.substring(0,3) + "-" + sDeclaration_Number_Phone.substring(3,7) + "-" + sDeclaration_Number_Phone.substring(7,11);
                                }


                                databaseReference.child(sKey).child("Object_Name").setValue(sObject_Name.replaceAll(" ", ""));
                                databaseReference.child(sKey).child("Old_Address").setValue(sOld_Address);
                                databaseReference.child(sKey).child("New_Address").setValue(sNew_Address);
                                databaseReference.child(sKey).child("Object_Manager").setValue(sObject_Manager);
                                databaseReference.child(sKey).child("Manager_General_Telephone").setValue(sManager_General_Telephone_com);
                                databaseReference.child(sKey).child("Manager_Cell_Phone").setValue(sManager_Cell_Phone_com);
                                databaseReference.child(sKey).child("Declaration_Number_Phone").setValue(sDeclaration_Number_Phone_com);
                                databaseReference.child(sKey).child("By_Place").setValue(By_Place_Select);
                                databaseReference.child(sKey).child("Jurisdiction_Center").setValue(Jurisdiction_Center_Select);//관할센터
                                databaseReference.child(sKey).child("Num").setValue("1");

                                databaseReference.child(sKey).child(Detail_Card).child("1").child("By_Case_Cause").setValue(By_Case_Cause_cv); //사례원인별
                                databaseReference.child(sKey).child(Detail_Card).child("1").child("Factors_Stack").setValue(Case_Stack);
                                databaseReference.child(sKey).child(Detail_Card).child("1").child("Factors_Position").setValue(Con_Case_Stack);

                                databaseReference.child(sKey).child(Detail_Card).child("1").child("Object_Name").setValue(sObject_Name);

                                databaseReference.child(sKey).child(Detail_Card).child("1").child("Reported_Content").setValue(sReported_Content); //조치내용

                                String sReporting_Time_Set = sReporting_Time.substring(0,4) + "년 " + sReporting_Time.substring(4,6) + "월 " + sReporting_Time.substring(6,8) + "일 " +
                                        sReporting_Time.substring(8,10) + "시" + sReporting_Time.substring(10,12) + "분";

                                databaseReference.child(sKey).child(Detail_Card).child("1").child("Reporting_Time").setValue(sReporting_Time_Set); //신고시각


                                Object_Name.setText("");
                                Old_Address.setText("");
                                New_Address.setText("");
                                Reporting_Time.setText("");
                                Object_Manager.setText("");
                                Manager_General_Telephone.setText("");
                                Manager_Cell_Phone.setText("");
                                Reported_Content.setText("");

                                Toast.makeText(getApplicationContext(),"저장되었습니다.",Toast.LENGTH_SHORT).show();
                                defstat.child("By_Place").child(By_Place).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.child("By_Place").child(By_Place).child(sReporting_Time.substring(0, 4)).child(sReporting_Time.substring(4, 6)).getValue() != null) {
                                            int value = (int) snapshot.child("By_Place").child(sReporting_Time.substring(0, 4)).child(sReporting_Time.substring(4, 6)).getValue(Integer.class);//저장된 값을 숫자로 받아오고
                                            value += 1;//숫자를 1 증가시켜서
                                            defstat.child("By_Place").child(By_Place).child(sReporting_Time.substring(0, 4)).child(sReporting_Time.substring(4, 6)).setValue(value);//저장
                                        }
                                        else{
                                            defstat.child("By_Place").child(By_Place).child(sReporting_Time.substring(0, 4)).child(sReporting_Time.substring(4, 6)).setValue(1);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
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
                                Toast.makeText(getApplicationContext(),"상호명을 입력하지 않으셨습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"오류!오류! 예방과에 연락주시기 바랍니다.",Toast.LENGTH_SHORT).show();
                    }
                });
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
