package com.hackathon.travelsdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hackathon.travelsdream.DB.Actividades;
import com.hackathon.travelsdream.ListViews.ListViewActivitiesAdapter;
import com.hackathon.travelsdream.model.Actividad;

import java.util.ArrayList;

public class ListActividadesView extends AppCompatActivity {

    private ArrayList<Actividad> ActList;
    private static ListView listView;
    private static ListViewActivitiesAdapter AdapterCreated;

    int idAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_actividades_view);

        ActList = new ArrayList<>();
        listView = findViewById(R.id.Lv_Actividades);

        showAct();

        listView.setClickable(true);
        try {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Actividad seleccionado = (Actividad) listView.getItemAtPosition(position);
                    idAct = seleccionado.getIdA();
                    //goToActUpDate(idAct);
                }
            });

        } catch (Exception e){
            Toast.makeText(this, "Error " + e, Toast.LENGTH_LONG).show();
        }

    }

    public void showAct(){

        int idA;
        String nick;
        String name;
        String departamento;
        String municipio;
        String place;
        String descrip;
        String picture;
        String price;
        String tipo;

        Actividades DBconection = new Actividades(this);
        String sentence = "SELECT*FROM actividades";

        Cursor result = DBconection.getData(sentence, null);

        try {

            result.moveToFirst();
            do {

                idA = result.getInt(0);
                nick= result.getString(1);
                name =result.getString(2);
                departamento = result.getString(3);
                municipio = result.getString(4);
                place = result.getString(5);
                descrip = result.getString(6);
                picture = result.getString(7);
                price = result.getString(8);
                tipo = result.getString(9);

                Actividad newActiv = new Actividad (nick,name,departamento,municipio,place,descrip,picture,price,tipo);
                newActiv.setIdA(idA);

                ActList.add(newActiv);

            }while (result.moveToNext());

            AdapterCreated = new ListViewActivitiesAdapter(this, ActList);
            listView.setAdapter(AdapterCreated);

        }catch (Exception e){

            Toast.makeText(this , "No se pudo generar equipos  " + e, Toast.LENGTH_SHORT).show();

        } finally {

            result.close();

        }

    }

    /*public void goToActUpDate(int idAct){

        Intent intentUpDate = new Intent(this, EquipUpD.class);
        intentUpDate.putExtra("idA", idAct);
        startActivity(intentUpDate);

    }*/

    public void goToCreateAct(View view){

        Intent goToCA = new Intent(this, ActivityCreate.class);
        startActivity(goToCA);

    }
}