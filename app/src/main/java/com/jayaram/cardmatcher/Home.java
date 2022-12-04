package com.jayaram.cardmatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         setTitle("Home");
        Button btn = findViewById(R.id.btn_nav);
        Button btn2 = findViewById(R.id.btn_card);
        Button btn3 = findViewById(R.id.btn_stats);
        Button btn4 = findViewById(R.id.btn_fillblanks);
        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this,MainActivity.class);
            startActivity(intent);
        });
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this,Simon_says.class);
            startActivity(intent);
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,statsActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finishAffinity();
        finishAndRemoveTask();
    }
}