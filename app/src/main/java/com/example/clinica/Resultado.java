package com.example.clinica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle params = intent.getExtras();
            String qtdPacientes = params.getString("qtdPacientes");
            String qtdPacientesMaisVelho = params.getString("qtdPacienteValidacaoIdade");
            String nomePacienteMaisVelho = params.getString("nomePacienteMaisVelho");
            String idadePacienteMaisVelho = params.getString("idadePacienteMaisVelho");

            TextView labelQtdPacientes = (TextView) findViewById(R.id.labelQtdPaciente);
            TextView labelQtdPacientesValidacao = (TextView) findViewById(R.id.labelIdadePaciente);
            TextView labelNomePacMaisVelho = (TextView) findViewById(R.id.labelNomePaciente);
            TextView labelIdadePacMaisVelho = (TextView) findViewById(R.id.labelIdadePaciente);

            labelQtdPacientes.setText(labelQtdPacientes.getText().toString() + qtdPacientes);
            labelQtdPacientesValidacao.setText(labelQtdPacientesValidacao.getText().toString() + qtdPacientesMaisVelho);
            labelNomePacMaisVelho.setText(labelNomePacMaisVelho.getText().toString() + nomePacienteMaisVelho);
            labelIdadePacMaisVelho.setText(labelIdadePacMaisVelho.getText().toString() + idadePacienteMaisVelho);
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}