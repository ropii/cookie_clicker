package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_play_demo, btn_play_web, btn_rules,btn_xo;
    AlertDialog ad;
    Intent it_rules,it_about,it_game,it_xo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play_demo = findViewById(R.id.btn_play_demo);
        btn_play_web = findViewById(R.id.btn_play_web);
        btn_rules = findViewById(R.id.btn_rules);
        btn_xo = findViewById(R.id.btn_xo);

        btn_xo.setOnClickListener(this);
        btn_play_demo.setOnClickListener(this);
        btn_play_web.setOnClickListener(this);
        btn_rules.setOnClickListener(this);
    }

    public void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("play on web");
        builder.setMessage("are you sure?");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://orteil.dashnet.org/cookieclicker/"));
                startActivity(it);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();


            }
        });
        ad = builder.create();
        ad.show();
    }

    @Override
    public void onClick(View view) {
        if(view==btn_xo){
            it_xo = new Intent(this, Tictactoe.class);
            startActivity(it_xo);
        }
        if (view==btn_play_web){
            alert();
        }

        if(view==btn_rules){
            it_rules = new Intent(this, rules.class);
            startActivity(it_rules);

        }

        if(view==btn_play_demo){
            it_game = new Intent(this, game.class);
            startActivity(it_game);

        }
    }



}