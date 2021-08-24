package com.example.fire_information_apparatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class manual_Activity_2 extends Activity {

    ImageButton before_page;
    manual_Activity_1 manual_activity_1 = (manual_Activity_1)manual_Activity_1.manual_Activity_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_manual_2);


        before_page = findViewById(R.id.before_page);

        before_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void mOnClose(View view) {
        manual_activity_1.finish();
        finish();
    }
}