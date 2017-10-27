package com.example.karim.prodigyv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_log_in;
    @Override
    protected void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_log_in = (Button) findViewById(R.id.btn_login);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(MainActivity.this,chose_charchter_layout.class);
                Intent intent1 = new Intent(MainActivity.this, map_home_layout.class);
                // Intent intent2=new Intent(MainActivity.this,.class);
                MainActivity.this.startActivity(intent1);
            }
        });
    }



    public void SignUpClick(View v)
    {
        Intent i = new Intent(MainActivity.this,Sign_up.class);
        startActivity(i);
    }

        }

