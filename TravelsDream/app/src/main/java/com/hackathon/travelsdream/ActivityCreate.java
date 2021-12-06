package com.hackathon.travelsdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackathon.travelsdream.DB.Actividades;
import com.hackathon.travelsdream.DB.Proveedores;

public class ActivityCreate extends AppCompatActivity {

    private EditText name, depart, munic, place, descrip, price;

    private Spinner desplegTipo;

    private SharedPreferences UserOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        name = findViewById(R.id.Tx_NameAct);
        depart = findViewById(R.id.Tx_Depart);
        munic = findViewById(R.id.Tx_Muni);
        place = findViewById(R.id.Tx_Place);
        descrip = findViewById(R.id.Tx_Descp);
        price = findViewById(R.id.Tx_Price);

        desplegTipo = findViewById(R.id.spinner);
        String [] opciones = {"agua","arqueología","avistamiento","caminar","cascadas","alimentación","hospedaje","montaña","Arte","lugares","otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_control,opciones);
        desplegTipo.setAdapter(adapter);

        UserOn =getSharedPreferences("UserOn", Context.MODE_PRIVATE);

    }

    public void ActCreat(View view){

        String nick_S, name_S, depart_S, munic_S, place_S, descrip_S, price_S, tipo_S, picture_S = null;

        nick_S = UserOn.getString("email", "");
        name_S = name.getText().toString();
        depart_S = depart.getText().toString();
        munic_S = munic.getText().toString();
        place_S = place.getText().toString();
        descrip_S = descrip.getText().toString();
        price_S = price.getText().toString();

        tipo_S = desplegTipo.getSelectedItem().toString();

        switch (tipo_S){

            case "agua":
                picture_S = "R.drawable.agua";
                break;
            case "arqueología":
                picture_S = "R.drawable.arquiologia";
                break;
            case "avistamiento":
                picture_S = "R.drawable.avistamiento";
                break;
            case "caminar":
                picture_S = "R.drawable.caminar";
                break;
            case "cascadas":
                picture_S = "R.drawable.cascadas";
                break;
            case "alimentación":
                picture_S = "R.drawable.alimentacion";
                break;
            case "hospedaje":
                picture_S = "R.drawable.hospedaje";
                break;
            case "montaña":
                picture_S = "R.drawable.montana";
                break;
            case "Arte":
                picture_S = "R.drawable.Arte";
                break;
            case "lugares":
                picture_S = "R.drawable.lugares";
                break;
            case "otro":
                picture_S = "R.drawable.otro";
                break;

        }

        if (name_S.length()>0 && depart_S.length()>0 && munic_S.length()>0 && place_S.length()>0 && descrip_S.length()>0 && price_S.length()>0 && tipo_S.length()>0){

            Actividades DBConection = new Actividades(this);
            String insert_query = "INSERT INTO actividades(nick, name, departamento, municipio, place, descrip, picture, precio, tipo)" +
                    "VALUES ('"+nick_S+"','"+name_S+"','"+depart_S+"','"+munic_S+"','"+place_S+"','"+descrip_S+"','"+picture_S+"','"+price_S+"','"+tipo_S+"')";

            boolean success = DBConection.insertData(insert_query);
            try {
                if (success) {
                    Toast.makeText(this, "Actividad guardada con éxito", Toast.LENGTH_LONG).show();
                    Intent goToAct = new Intent(this, ListActividadesView.class);

                    String sentence = "SELECT*FROM actividades WHERE nick = ?";
                    String[] params = new String[]{nick_S};

                    Cursor result = DBConection.getData(sentence, params);
                    result.moveToFirst();

                    int idA = result.getInt(0);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("actividades");

                    myRef.child("Actividad_" + idA).child("id").setValue(idA);
                    myRef.child("Actividad_" + idA).child("nick").setValue(nick_S);
                    myRef.child("Actividad_" + idA).child("name").setValue(name_S);
                    myRef.child("Actividad_" + idA).child("departamento").setValue(depart_S);
                    myRef.child("Actividad_" + idA).child("municipio").setValue(munic_S);
                    myRef.child("Actividad_" + idA).child("place").setValue(place_S);
                    myRef.child("Actividad_" + idA).child("descrip").setValue(descrip_S);
                    myRef.child("Actividad_" + idA).child("picture").setValue(picture_S);
                    myRef.child("Actividad_" + idA).child("precio").setValue(price_S);
                    myRef.child("Actividad_" + idA).child("tipo").setValue(tipo_S);


                    startActivity(goToAct);

                } else {
                    Toast.makeText(this, "Error al guardar la actividad", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Toast.makeText(this, "El error es: " + e, Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Debe Completar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

}