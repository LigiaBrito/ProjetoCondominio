package com.example.projetocondominio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btnAlterar = findViewById(R.id.btnAlterar);
        Button btnDeletar = findViewById(R.id.btnDeletar);
        Button btnInserir = findViewById(R.id.btnInserir);
        Button btnMostrar = findViewById(R.id.btnMostrar);

        btnAlterar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, UpdateEntrada.class);
                startActivity(intent);
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, DeletarMorador.class);
                startActivity(intent);
            }
        });

        btnInserir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        btnMostrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MostrarEntradaSaida.class);
                startActivity(intent);
            }
        });

    }
}