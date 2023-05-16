package com.example.projetocondominio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class UpdateEntrada extends AppCompatActivity {

    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
    private SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private SimpleDateFormat inputTimeFormat = new SimpleDateFormat("HHmm", Locale.getDefault());
    private SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private boolean isFormatting;
    TextView tvDataEntrada;
    TextView tvHoraEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entrada);

        Button btnAlterar = findViewById(R.id.buttonAlterar);
        TextView tvIdAlterar = findViewById(R.id.edit_idAlterar);
        TextView tvNome = findViewById(R.id.edit_nomeUpdate);
        TextView tvNumeroCondominio = findViewById(R.id.edit_numeroCondominioUpdate);
        tvHoraEntrada = findViewById(R.id.edit_horaUpdate);
        tvDataEntrada = findViewById(R.id.edit_dataUpdate);

        tvDataEntrada.addTextChangedListener(dateTextWatcher);
        tvHoraEntrada.addTextChangedListener(timeTextWatcher);

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

    private TextWatcher dateTextWatcher = new TextWatcher() {
        private boolean isFormatting;
        private int prevLength;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            prevLength = s.length();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isFormatting) {
                // Se estivermos formatando o texto, não fazer nada
                return;
            }

            // Formatando o texto enquanto o usuário digita
            String inputText = s.toString();

            if (inputText.length() == 8 && inputText.length() > prevLength) {
                try {
                    Date date = inputDateFormat.parse(inputText);
                    String outputText = outputDateFormat.format(date);

                    isFormatting = true;
                    tvDataEntrada.setText(outputText);
                    int selectionStart = start + count;
                    int selectionEnd = selectionStart + (outputText.length() - (prevLength - before));
                    Selection.setSelection((Spannable) tvDataEntrada.getText(), selectionStart, selectionEnd);
                    isFormatting = false;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    private TextWatcher timeTextWatcher = new TextWatcher() {
        private boolean isFormatting;
        private int prevLength;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            prevLength = s.length();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isFormatting) {
                // Se estivermos formatando o texto, não fazer nada
                return;
            }

            // Formatando o texto enquanto o usuário digita
            String inputText = s.toString();

            if (inputText.length() == 4 && inputText.length() > prevLength) {
                try {
                    String outputText = formatTime(inputText);

                    isFormatting = true;
                    tvHoraEntrada.setText(outputText);
                    int selectionStart = start + count;
                    int selectionEnd = selectionStart + (outputText.length() - (prevLength - before));
                    Selection.setSelection((Spannable) tvHoraEntrada.getText(), selectionStart, selectionEnd);
                    isFormatting = false;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private String formatTime(String inputTime) throws ParseException {
        Date time = inputTimeFormat.parse(inputTime);
        return outputTimeFormat.format(time);
    }

}
