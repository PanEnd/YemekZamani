package com.example.yemekzamani;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class YemekTarifiFragment extends Fragment {

    TextView yemekAdi;
    TextView yemekMalzemeleri;
    TextView yemekTarifi;
    String[] tarifListesi;

    public YemekTarifiFragment() {

    }

    public static YemekTarifiFragment newInstance(String param1, String param2) {
        YemekTarifiFragment fragment = new YemekTarifiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_yemek_tarifi, container, false);

        Bundle bundle=getArguments();
        final String bndlYemek = bundle.getString("yemekAdi");
        yemekTarifiBul(bndlYemek);

        yemekAdi = v.findViewById(R.id.yemekBaslik);
        yemekMalzemeleri = v.findViewById(R.id.malzemeler);
        yemekTarifi = v.findViewById(R.id.tarif);

        yemekAdi.setText(bndlYemek);
        yemekMalzemeleri.setText(yemekMalzemeOlustur(tarifListesi));
        yemekTarifi.setText(yemekTarifOlustur(tarifListesi));
        return v;
    }

    public void yemekTarifiBul(String yemek){
        switch (yemek){
            case "1. Menemen":
                tarifListesi = getResources().getStringArray(R.array.menemen_malzeme_tarif);
                break;
            case "2. Bulgur Pilavı":
                tarifListesi = getResources().getStringArray(R.array.bulgurPilavı_malzeme_tarif);
                break;
            case "3. Pirinç Pilavı":
                tarifListesi = getResources().getStringArray(R.array.pirincPilav_malzeme_tarif);
                break;
            case "4. Mercimek Çorbası":
                tarifListesi = getResources().getStringArray(R.array.mercimekÇorbası_malzeme_tarif);
                break;
            case "5. Et Sote":
                tarifListesi = getResources().getStringArray(R.array.etSote_malzeme_tarif);
                break;
            case "6. Tavuk Sote":
                tarifListesi = getResources().getStringArray(R.array.tavukSote_malzeme_tarif);
                break;
            case "7. Brokoli Salatası":
                tarifListesi = getResources().getStringArray(R.array.brokoliSalata_malzeme_tarif);
                break;
            case "8. Havuç Tarator":
                tarifListesi = getResources().getStringArray(R.array.havucTarator_malzeme_tarif);
                break;
            case "9. Çökertme Kebabı":
                tarifListesi = getResources().getStringArray(R.array.cokertme_malzeme_tarif);
                break;
            default:
                break;
        }
    }

    public String yemekMalzemeOlustur(String[] tumListe){
        String malzemeler = "*";
        for(int i = 0; i<tumListe.length-1; i++){
            malzemeler += tumListe[i];
            malzemeler +="*";
        }
        return malzemeler;
    }

    public String yemekTarifOlustur(String[] tumListe){
        String tarif = "";
        int listUzunlugu = tumListe.length - 1;
        tarif = tumListe[listUzunlugu];
        return tarif;
    }

}
