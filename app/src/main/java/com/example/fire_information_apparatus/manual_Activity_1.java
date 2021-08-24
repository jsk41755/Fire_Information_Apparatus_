package com.example.fire_information_apparatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class manual_Activity_1 extends Activity {
    ImageButton next_page;
    public static Activity manual_Activity_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_manual_1);

        manual_Activity_1 = manual_Activity_1.this;

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
        finish();
    }
}