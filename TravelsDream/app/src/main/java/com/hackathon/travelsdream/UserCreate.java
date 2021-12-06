package com.hackathon.travelsdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackathon.travelsdream.DB.Mochileros;
import com.hackathon.travelsdream.DB.Proveedores;

public class UserCreate extends AppCompatActivity {

    private EditText Nick, name, lastname, mobile, email, passW, CpassW;
    private RadioButton RBM, RBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

        Nick = findViewById(R.id.Nick);
        name = findViewById(R.id.Tx_Name);
        lastname = findViewById(R.id.Tx_LastName);
        mobile = findViewById(R.id.Tx_Mobile);
        email = findViewById(R.id.Tx_EmailNU);
        passW = findViewById(R.id.Tx_PassWordNU);
        CpassW = findViewById(R.id.Tx_CPassWordNU);

        RBM = findViewById(R.id.RBM);
        RBP = findViewById(R.id.RBP);

    }

    public void UserCreat(View view){

        String Nick_S, name_S, lastname_S, mobile_S, email_S, passW_S, CpassW_S;

        Nick_S = Nick.getText().toString();
        name_S = name.getText().toString();
        lastname_S = lastname.getText().toString();
        mobile_S = mobile.getText().toString();
        email_S = email.getText().toString();
        passW_S = passW.getText().toString();
        CpassW_S = CpassW.getText().toString();

        if(Nick_S.length()>0 && name_S.length()>0 && lastname_S.length()>0 && mobile_S.length()>0 && email_S.length()>0 && passW_S.length()>0) {

            if (RBM.isChecked()) {

                if (passW_S.equals(CpassW_S)) {

                    Mochileros DBConection = new Mochileros(this);
                    String insert_query = "INSERT INTO mochileros(nick, name, lastName, email, mobile, passWord)" +
                            "VALUES ('"+Nick_S+"','"+name_S+"','"+lastname_S+"','"+email_S+"','"+mobile_S+"','"+passW_S+"')";

                    boolean success = DBConection.insertData(insert_query);

                    if (success) {
                        Toast.makeText(this, "Persona guardado con éxito", Toast.LENGTH_LONG).show();
                        Intent goToAct = new Intent(this, ListActividadesView.class);

                        String sentence = "SELECT*FROM mochileros WHERE nick = ?";
                        String[] params = new String[]{Nick_S};

                        Cursor result = DBConection.getData(sentence,params);
                        result.moveToFirst();

                        int idM = result.getInt(0);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("mochileros");

                        myRef.child("Mochilero_" + email_S).child("id").setValue(idM);
                        myRef.child("Mochilero_" + email_S).child("nick").setValue(Nick_S);
                        myRef.child("Mochilero_" + email_S).child("name").setValue(name_S);
                        myRef.child("Mochilero_" + email_S).child("lastName").setValue(lastname_S);
                        myRef.child("Mochilero_" + email_S).child("email").setValue(email_S);
                        myRef.child("Mochilero_" + email_S).child("mobile").setValue(mobile_S);
                        myRef.child("Mochilero_" + email_S).child("passWord").setValue(passW_S);

                        startActivity(goToAct);

                    } else {
                        Toast.makeText(this, "Error al guardar la persona", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }

            }else if (RBP.isChecked()) {

                if (passW_S.equals(CpassW_S)) {

                    Proveedores DBConection = new Proveedores(this);
                    String insert_query = "INSERT INTO proveedores(nick, name, lastName, email, mobile, passWord)" +
                            "VALUES ('"+Nick_S+"','"+name_S+"','"+lastname_S+"','"+email_S+"','"+mobile_S+"','"+passW_S+"')";

                    boolean success = DBConection.insertData(insert_query);
                    if (success) {
                        Toast.makeText(this, "Persona guardado con éxito", Toast.LENGTH_LONG).show();
                        Intent goToAct = new Intent(this, ListActividadesView.class);

                        String sentence = "SELECT*FROM proveedores WHERE nick = ?";
                        String[] params = new String[]{Nick_S};

                        Cursor result = DBConection.getData(sentence,params);
                        result.moveToFirst();

                        int idP = result.getInt(0);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("proveedores");

                        myRef.child("Proveedor_" + email_S).child("nick").setValue(idP);
                        myRef.child("Proveedor_" + email_S).child("nick").setValue(Nick_S);
                        myRef.child("Proveedor_" + email_S).child("name").setValue(name_S);
                        myRef.child("Proveedor_" + email_S).child("lastName").setValue(lastname_S);
                        myRef.child("Proveedor_" + email_S).child("email").setValue(email_S);
                        myRef.child("Proveedor_" + email_S).child("mobile").setValue(mobile_S);
                        myRef.child("Proveedor_" + email_S).child("passWord").setValue(passW_S);

                        startActivity(goToAct);

                    } else {
                        Toast.makeText(this, "Error al guardar la persona", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }

            }

        }else{
            Toast.makeText(this, "Debe Completar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

}