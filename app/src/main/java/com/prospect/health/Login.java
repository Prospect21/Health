package com.prospect.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button dataOutput;
    EditText user , password;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataOutput = (Button) findViewById(R.id.buttonSingup);
        user=(EditText)findViewById(R.id.editEmail);
        password=(EditText)findViewById(R.id.editPass);

        dataOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setIcon(R.mipmap.ic_launcher).
                        setTitle("DATOS").
                        setMessage("Usuario: " + user.getText().toString()+ "\nContraseña: " + password.getText().toString());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Handler handler = new Handler();
                //Llamamos al método postDelayed
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    }
                }, 2000); // 2 segundos de "delay"


            }

        });
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void registre(View v){
        Intent registre = new Intent(this, Registre.class);
        startActivity(registre);
    }
}
