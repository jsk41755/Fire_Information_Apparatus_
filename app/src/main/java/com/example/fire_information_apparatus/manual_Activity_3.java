package com.example.fire_information_apparatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class manual_Activity_3 extends Activity {
    ImageButton next_page;
    TextView txtLink, textView;
    public static Activity manual_Activity_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_manual3);

        manual_Activity_3 = manual_Activity_3.this;

        txtLink = findViewById(R.id.txtLink);
        textView = findViewById(R.id.textView);

        next_page = findViewById(R.id.next_page);

        textView.setText("이 앱은 자동화재 탐지설비의 감지기 오작동에 의해 출동한 소방대원의 활동을 돕고 대상물을 체계적이고 효율적으로 관리하고자 만들어졌습니다.");

        txtLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("https://youtu.be/5HJWTCRCjXg"));

                startActivity(intent);

            }
        });

        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staticIntent = new Intent(manual_Activity_3.this, manual_Activity_1.class);
                startActivity(staticIntent);
            }
        });
    }

    public void mOnClose(View view) {
        finish();
    }
}