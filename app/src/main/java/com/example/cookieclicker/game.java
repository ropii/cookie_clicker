package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.Random;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class game extends AppCompatActivity implements View.OnClickListener {
    Button btn_back_to_main, btn_buy;
    Intent it_main;
    int point = 0, tap = 1, price = 5;
    ImageButton ibtn_cookie;
    TextView tv_points, tv_taps;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_points = findViewById(R.id.tv_points);
        tv_taps = findViewById(R.id.tv_taps);


        btn_back_to_main = findViewById(R.id.btn_back_to_main);
        btn_back_to_main.setOnClickListener(this);

        btn_buy = findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(this);

        ibtn_cookie = findViewById(R.id.ibtn_cookie);
        ibtn_cookie.setOnClickListener(this);

        sp = getSharedPreferences("details", MODE_PRIVATE);
        editor = sp.edit();


    }

    @Override
    public void onResume() {
        super.onResume();
        point=sp.getInt("points",0);
        tap=sp.getInt("taps",1);
        price=sp.getInt("price",5);
        tv_taps.setText("Taps: " + tap);
        btn_buy.setText("buy a tap at " + price);
        tv_points.setText("Points: " + point);
    }

    @Override
    public void onPause() {
        super.onPause();
        editor.putInt("points", point);
        editor.putInt("taps", tap);
        editor.putInt("price", price);
        editor.commit();
    }


    @Override
    public void onClick(View view) {
        if (view == btn_back_to_main) {
            it_main = new Intent(this, MainActivity.class);
            startActivity(it_main);
        }
        if (view == ibtn_cookie) {
            point += tap;
            tv_points.setText("Points: " + point);
            Random rand = new Random();
            int random_num = rand.nextInt(10);
            if (random_num==7){
                Toast.makeText(game.this, "זה ממש פסיכוטי", Toast.LENGTH_SHORT).show();
            }
        }

        if (view == btn_buy) {
            if (point >= price) {
                point = point - price;
                price *= 2;
                tap += 1;
                tv_taps.setText("Taps: " + tap);
                btn_buy.setText("buy a tap at " + price);
                tv_points.setText("Points: " + point);
            } else {
                Toast.makeText(game.this, "you need more points", Toast.LENGTH_SHORT).show();

            }

        }
    }
}