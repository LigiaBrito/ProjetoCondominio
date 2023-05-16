package com.example.projetocondominio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeletarMorador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_morador);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference moradorRef = database.getReference("entrada");

        TextView tvIdDeletar = findViewById(R.id.edit_idDeletar);
        String entradaDeletarId = tvIdDeletar.getText().toString();

        DatabaseReference moradorToDeleteRef = moradorRef.child(entradaDeletarId);

        Button btnDeletar = findViewById(R.id.buttonDeletar);
        btnDeletar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView tvIdDeletar = findViewById(R.id.edit_idDeletar);
                String entradaDeletarId = tvIdDeletar.getText().toString();

                DatabaseReference moradorToDeleteRef = moradorRef.child(entradaDeletarId);

                moradorToDeleteRef.removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Valor deletado com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DeletarMorador.this, Home.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Houve um erro...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DeletarMorador.this, Home.class);
                                startActivity(intent);
                            }
                        });
            }
        });

    }

}