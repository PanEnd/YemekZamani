package com.example.yemekzamani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMalzeme = findViewById(R.id.malzemeTarif);
        Button btnTarif = findViewById(R.id.yemekTarifi);
        Button btnNot = findViewById(R.id.btn_note);


        btnMalzeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis = new Intent(MainActivity.this, MalzemeEkranActivity.class);
                startActivity(gecis);
            }
        });

        btnTarif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis = new Intent(MainActivity.this, YemekEkranActivity.class);
                startActivity(gecis);
            }
        });

        btnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(gecis);
            }
        });
    }
}
