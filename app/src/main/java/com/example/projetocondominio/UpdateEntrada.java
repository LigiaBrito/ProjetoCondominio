package com.example.projetocondominio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UpdateEntrada extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entrada);

        Button btnAlterar = findViewById(R.id.buttonAlterar);
        TextView tvIdAlterar = findViewById(R.id.edit_idAlterar);
        TextView tvNome = findViewById(R.id.edit_nomeUpdate);
        TextView tvNumeroCondominio = findViewById(R.id.edit_numeroCondominioUpdate);
        TextView tvHoraEntrada = findViewById(R.id.edit_horaUpdate);
        TextView tvDataEntrada = findViewById(R.id.edit_dataUpdate);
        btnAlterar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String entradaId = tvIdAlterar.getText().toString(); // ID da entrada a ser atualizada
                String novoNome = tvNome.getText().toString(); // Novo valor para o nome
                String novoNumeroCondominio = tvNumeroCondominio.getText().toString(); // Novo valor para o número do condomínio
                String novaHoraEntrada = tvHoraEntrada.getText().toString(); // Novo valor para a hora de entrada
                String novaData = tvDataEntrada.getText().toString(); // Novo valor para a data

                EntradaMorador novaEntradaMorador = new EntradaMorador(novoNome, novoNumeroCondominio, novaHoraEntrada, novaData);
                DatabaseReference entradaRef = FirebaseDatabase.getInstance().getReference("entrada").child(entradaId);

                entradaRef.child("nome").setValue(novoNome);
                entradaRef.child("numeroCondominio").setValue(novoNumeroCondominio);
                entradaRef.child("horaEntrada").setValue(novaHoraEntrada);
                entradaRef.child("data").setValue(novaData);

                Toast.makeText(getApplicationContext(), "Valor alterado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });


    }
}