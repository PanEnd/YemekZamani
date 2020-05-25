package com.example.yemekzamani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MalzemeEkranActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    ListView liste;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malzeme_ekran);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn = findViewById(R.id.btnMalzeme);
        liste = findViewById(R.id.malzemeListesi);

        String[] malzemeler = getResources().getStringArray(R.array.list_malzeme);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, malzemeler);
        liste.setChoiceMode(liste.CHOICE_MODE_MULTIPLE);
        liste.setAdapter(adapter);

        btn.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {

        String mesaj = "->";
        SparseBooleanArray checked = liste.getCheckedItemPositions();
        ArrayList<String> secilmisMalzemeler = new ArrayList<String>();
        for(int i=0 ; i< checked.size(); i++){
            int pos = checked.keyAt(i);
            if(checked.valueAt(i)){
                secilmisMalzemeler.add(adapter.getItem(pos));
            }
        }
        String[] secilenListe = new String[secilmisMalzemeler.size()];
        for(int i=0; i< secilenListe.length; i++){
            secilenListe[i] = secilmisMalzemeler.get(i);
        }

        mesaj = yapılabilenYemekler(secilenListe);
        AlertEkran(mesaj);
        //Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_LONG).show();
    }

    public ArrayList<String> yemekMalzemeListeOlustur(String[] tumListe){
        ArrayList<String> malzemeler = new ArrayList<String>();
        for(int i = 0; i<tumListe.length-1; i++){
            malzemeler.add(tumListe[i]);
        }
        return malzemeler;
    }

    public String yapılabilenYemekler(String[] secilenlerListe){
        String yapılabilecekYemekler = "";
        String[] yemekMalzemeTarif = getResources().getStringArray(R.array.menemen_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Menemen  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.bulgurPilavı_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Bulgur Pilavı  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.pirincPilav_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Pirinç Pilavı  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.mercimekÇorbası_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Mercimek Çorbası  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.etSote_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Et Sote  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.tavukSote_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Tavuk Sote  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.brokoliSalata_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Brokoli Salatası  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.havucTarator_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Havuç Tarator  ";

        yemekMalzemeTarif = getResources().getStringArray(R.array.cokertme_malzeme_tarif);
        if(yemekBul(yemekMalzemeTarif,secilenlerListe))
            yapılabilecekYemekler += "Çökertme Kebabı  ";

        return yapılabilecekYemekler;
    }
    public Boolean yemekBul(String[] yemekTarifListesi, String[] secilenlerListe){
        ArrayList<String> malzemeler = yemekMalzemeListeOlustur(yemekTarifListesi);
        ArrayList<String> ortakMalzemler = new ArrayList<String>(malzemeler);
        ortakMalzemler.retainAll(Arrays.asList(secilenlerListe));
        if(ortakMalzemler.containsAll(malzemeler)){
            return true;
        }
        else{
            return false;
        }
    }

    public void AlertEkran(String yemekler){
        AlertDialog.Builder builder = new AlertDialog.Builder(MalzemeEkranActivity.this);
        builder.setTitle("Yapabileceğiniz Yemekler:");
        builder.setMessage(yemekler);
        builder.setNegativeButton("Geri Dön", null);
        builder.setPositiveButton("Yemekleri Yap", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MalzemeEkranActivity.this, YemekEkranActivity.class);
                startActivity(intent);
            }
        });

        builder.setNeutralButton("Malzemeleri Al", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri link = Uri.parse("https://www.migros.com.tr/");
                Intent linkIntent = new Intent(Intent.ACTION_VIEW, link);

                Uri location = Uri.parse("geo:0,0?q=migros");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                boolean isIntentSafe = activities.size() > 0;
                if(isIntentSafe)
                    startActivity(mapIntent);
                else
                    startActivity(linkIntent);
            }
        });
        builder.show();
    }
}
