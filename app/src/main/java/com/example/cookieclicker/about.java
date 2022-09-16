package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class about extends AppCompatActivity implements View.OnClickListener {


    Button btn_back_to_main;
    Intent it_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btn_back_to_main = findViewById(R.id.btn_back_to_main);

        btn_back_to_main.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==btn_back_to_main){
            it_main = new Intent(this, MainActivity.class);
            startActivity(it_main);

        }
    }

}
