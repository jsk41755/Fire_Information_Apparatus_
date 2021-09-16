package com.example.fire_information_apparatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class manual_Activity_1 extends Activity {
    ImageButton next_page, before_page;
    public static Activity manual_Activity_1;

    manual_Activity_3 manual_activity_3 = (manual_Activity_3)manual_Activity_3.manual_Activity_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_manual_1);

        manual_Activity_1 = manual_Activity_1.this;

        before_page = findViewById(R.id.before_page);

        before_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        next_page = findViewById(R.id.next_page);

        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staticIntent = new Intent(manual_Activity_1.this, manual_Activity_2.class);
                startActivity(staticIntent);
            }
        });
    }

    public void mOnClose(View v){
        manual_activity_3.finish();
        finish();
    }
}