package com.prospect.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class Registre extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Results Screen
    public void return0(View v) {
        Intent return0 = new Intent(this, MainActivity.class);
        startActivity(return0);
    }
    //Results Screen
    public void term(View v) {
        Intent term = new Intent(this, TerminosCondiciones.class);
        startActivity(term);
    }
}