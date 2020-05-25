package com.example.yemekzamani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class YemekEkranActivity extends AppCompatActivity{

    ListView liste;
    ArrayAdapter<String> adapter;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_ekran);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        liste = findViewById(R.id.yemekListesi);

        final String[] yemekler = getResources().getStringArray(R.array.list_yemek);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, yemekler);
        liste.setChoiceMode(liste.CHOICE_MODE_SINGLE);
        liste.setAdapter(adapter);

        manager = getSupportFragmentManager();
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(YemekEkranActivity.this, yemekler[position], Toast.LENGTH_SHORT).show();
                YemekTarifiFragment tarifEkran = new YemekTarifiFragment();
                Bundle bundle = new Bundle();
                bundle.putString("yemekAdi",yemekler[position]);
                tarifEkran.setArguments(bundle);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.yemekTarifEkrani, tarifEkran, "FragmentTarif");
                transaction.commit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
