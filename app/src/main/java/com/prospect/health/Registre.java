package com.prospect.health;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registre extends AppCompatActivity {

    private EditText mEdiTextName;
    private EditText mEditTextEmail;
    private EditText mEdiTextSurnames;
    private EditText mEdiTextWeight;
    private EditText mEditTextHeight;
    private EditText mEdiTextDate;
    private EditText mEdiTextPassword;
    private Button mButtonRegister;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //VAriables
    private String name="";
    private String email="";
    private String surnames="";
    private String weight="";
    private String height="";
    private String date="";
    private String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEdiTextName = (EditText) findViewById(R.id.nombre);
        mEditTextEmail = (EditText) findViewById(R.id.ingrese_su_correo);
        mEdiTextSurnames = (EditText) findViewById(R.id.apellidos);
        mEdiTextWeight = (EditText) findViewById(R.id.peso);
        mEditTextHeight = (EditText) findViewById(R.id.altura);
        mEdiTextDate = (EditText) findViewById(R.id.date);
        mEdiTextPassword = (EditText) findViewById(R.id.ingrese_su_password);
        mButtonRegister = (Button) findViewById(R.id.ButtonSendRegister);

        mButtonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                name = mEdiTextName.getText().toString();
                email = mEditTextEmail.getText().toString();
                surnames = mEdiTextSurnames.getText().toString();
                weight = mEdiTextWeight.getText().toString();
                height = mEditTextHeight.getText().toString();
                date = mEdiTextDate.getText().toString();
                password = mEdiTextPassword.getText().toString();
                /*name = "Prueba1";
                mDatabase.child("Usuario").setValue(name);*/

                registerUser();
            }
        });

    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put( "name", name);
                    map.put( "password", password);
                    map.put( "email", email);
                    map.put( "surnames", surnames);
                    map.put( "weight", weight);
                    map.put( "height", height);
                    map.put( "date", date);

                    String id= mAuth.getCurrentUser().getUid();
                    mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(Registre.this, MainActivity.class ));
                                Log.d("myTag", "Su registro ha sido exitoso");
                                Toast.makeText( Registre.this, "Su registro ha sido exitoso", Toast.LENGTH_SHORT).show();

                                finish();
                            }else{
                                Log.d("myTag", "EL rgistro fallo");
                                Toast.makeText( Registre.this, "EL rgistro fallo", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }else{
                    Toast.makeText( Registre.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Results Screen
    /*public void return0(View v) {
        Intent return0 = new Intent(this, MainActivity.class);
        startActivity(return0);
    }*/
    //Results Screen
    public void term(View v) {
        Intent term = new Intent(this, TerminosCondiciones.class);
        startActivity(term);
    }
}