package com.example.jsonvsbarcelona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    //    "features": [
//    {
//        "type": "Feature",
//            "properties": {
//        "CODI_CAPA": "P001",
//                "CAPA_GENERICA": "Internet i comunicacions",
//                "NOM_CAPA": "WiFi BCN",
//                "ED50_COORD_X": 432995552,
//                "ED50_COORD_Y": 4583916692,
//                "ETRS89_COORD_X": 432903597,
//                "ETRS89_COORD_Y": 4583715741,
//                "LONGITUD": 2.197302,
//                "LATITUD": 41.402184,
//                "EQUIPAMENT": "Punt de connexió Barcelona WiFi a la cruïlla del carrer Llacuna amb Pere IV",
//                "DISTRICTE": 10,
//                "BARRI": 68,
//                "NOM_DISTRICTE": "Sant Martí",
//                "NOM_BARRI": "el Poblenou",
//                "ADRECA": "C Pere IV, 164",
//                "TELEFON": null
//    },
//        "geometry": {
//        "type": "Point",
//                "coordinates": [
//        2.197302,
//                41.402184
//        ]
//    }
//    },

    RecyclerView recyclerView;
    ArrayList<String> adreca = new ArrayList<>();
    ArrayList<String> longitud = new ArrayList<>();
    ArrayList<String> latitud = new ArrayList<>();
    ArrayList<String> equip = new ArrayList<>();
    ArrayList<MapView> map = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleLayout);

        recyclerView.setLayoutManager(new
                LinearLayoutManager(getApplicationContext()));

        MyAdapter myAdapter = new MyAdapter(this,adreca,longitud,latitud,equip);
        recyclerView.setAdapter(myAdapter);


        //creamos el objeto json
        try
        {
            JSONObject jsonObject = new JSONObject(loadJsonObjectfromAssets());

            JSONArray wifiJSONArray = jsonObject.getJSONArray("features");
            Log.d(TAG, "onCreate: Elfitxer te etrades:  " + wifiJSONArray.length());

            for (int i = 0; i < wifiJSONArray.length(); i++)

            {
                JSONObject wifiRowDetail = wifiJSONArray.getJSONObject(i);
                JSONObject propertiesDetail = wifiRowDetail.getJSONObject("properties");
                longitud.add(propertiesDetail.getString("LONGITUD"));
                latitud.add(propertiesDetail.getString("LATITUD"));
                adreca.add(propertiesDetail.getString("ADRECA"));
                equip.add(propertiesDetail.getString("EQUIPAMENT"));



            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

    }


    private String loadJsonObjectfromAssets() {
        String json = null;

        try
        {

            InputStream input = getAssets().open("wifibarcelona.json");
            // miramos el numero de bytespara recorerlo
            int size = input.available();
            Log.d(TAG, "loadJsonObjectfromAssets: el fitxer es de : " + size);

            byte[] buffer = new byte[size];
            // leemos el archivo i guardamos en el buffer
            input.read(buffer);
            input.close();
            json = new String(buffer, "UTF-8"); //acepta todos los caracteres


        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return json;
    }

}