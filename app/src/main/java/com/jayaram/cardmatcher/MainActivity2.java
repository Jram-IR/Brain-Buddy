package com.jayaram.cardmatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         setTitle("Identify");
        Button bt_start;
        EditText et_name;

        bt_start = (Button) findViewById(R.id.bt_start);
        et_name = (EditText) findViewById(R.id.et_name);

        bt_start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (et_name.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity2.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(MainActivity2.this, Questions.class);
                    MainActivity2.this.startActivity(intent);
                    MainActivity2.this.finish();
                }
            }
        });
    }
}
