package com.example.projetocondominio;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.FirebaseApp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    private EditText nomeMoradorEditText;
    private EditText numeroCondominioEditText;
    private Button confirmarButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseApp.initializeApp(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        nomeMoradorEditText = findViewById(R.id.nome_morador_edittext);
        numeroCondominioEditText = findViewById(R.id.numero_condominio_edittext);
        confirmarButton = findViewById(R.id.confirmar_button);

        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeMorador = nomeMoradorEditText.getText().toString();
                String numeroCondominio = numeroCondominioEditText.getText().toString();
                String horaEntrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(new Date());

                EntradaMorador entradaMorador = new EntradaMorador(nomeMorador, numeroCondominio, horaEntrada);

                DatabaseReference entradaRef = databaseReference.child("entrada").push();
                entradaRef.setValue(entradaMorador);

                Toast.makeText(getApplicationContext(), "Entrada registrada com sucesso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity2.this, MostrarEntradaSaida.class);
                startActivity(intent);
            }
        });

    }
}