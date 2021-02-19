package com.mine.donemyself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button add_btn,view_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_btn = findViewById(R.id.au_btn);
        view_btn = findViewById(R.id.vd_btn);


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_user = new Intent(MainActivity.this,UserDetailPage.class);
                startActivity(add_user);
            }
        });

        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_details = new Intent(MainActivity.this,RecycleView.class);
                startActivity(view_details);
            }
        });
    }
}
