package com.hackathon.travelsdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackathon.travelsdream.DB.Mochileros;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences UserOn;
    private EditText Email, passW;
    private RadioButton RBMo,RBPr;
    String emailU, passWU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.Tx_EmailLogin);
        passW = findViewById(R.id.Tx_PassWLogin);

        RBMo = findViewById(R.id.RBMo);
        RBPr = findViewById(R.id.RBPr);

    }

    public void singIn(View view) {

        emailU = Email.getText().toString();
        passWU = passW.getText().toString();
        Intent goToList =new Intent(this, ListActividadesView.class);

        if (RBMo.isChecked()) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("mochilero");
            myRef.child("Mochilero_" + emailU).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        String saved_password = dataSnapshot.child("passWord").getValue().toString();
                        if (saved_password.equals(passWU)) {

                            UserOn = getSharedPreferences("UserOn", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = UserOn.edit();
                            editor.putString("email", emailU);
                            editor.commit();

                            startActivity(goToList);

                        } else {
                            Toast.makeText(MainActivity.this, "Usuario y/o contraseña no válidos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario y/o contraseña no válido", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(MainActivity.this, "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
                }
            });

        }else if (RBPr.isChecked()){

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("proveedores");
            myRef.child("Proveedor_" + emailU).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        String saved_password = dataSnapshot.child("passWord").getValue().toString();
                        if (saved_password.equals(passWU)) {

                            UserOn = getSharedPreferences("UserOn", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = UserOn.edit();
                            editor.putString("email", emailU);
                            editor.commit();

                            startActivity(goToList);

                        } else {
                            Toast.makeText(MainActivity.this, "Usuario y/o contraseña no válidos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario y/o contraseña no válido", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(MainActivity.this, "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(this, "Seleccione Mochilero o Proveedor", Toast.LENGTH_SHORT).show();
        }

    }

    public void goToCreate(View view){

        Intent goToC = new Intent(this, UserCreate.class);
        startActivity(goToC);

    }
}