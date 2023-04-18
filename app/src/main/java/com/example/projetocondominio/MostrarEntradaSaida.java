package com.example.projetocondominio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MostrarEntradaSaida extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<EntradaMorador> entradaMoradorList;
    private static final String TAG = "MostrarEntradaSaida";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_entrada_saida);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("entrada");

        entradaMoradorList = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entradaMoradorList.clear();
                for (DataSnapshot entradaSnapshot : dataSnapshot.getChildren()) {
                    EntradaMorador entradaMorador = entradaSnapshot.getValue(EntradaMorador.class);
                    entradaMoradorList.add(entradaMorador);
                }
                // Pass the list of EntradaMorador objects to the adapter
                // and display them in the RecyclerView
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        databaseReference.addValueEventListener(valueEventListener);

    }
}