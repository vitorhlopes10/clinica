package com.example.clinica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtNome;
    RadioButton radioBtnMasc;
    RadioButton radioBtnFem;
    EditText numberTxtPeso;
    EditText numberTxtIdade;
    EditText numberTxtAltura;
    Button btnVisualizarPacientes;
    Button btnCadastrar;

    ArrayList<Paciente> pacientes = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNome = findViewById(R.id.txtNome);
        radioBtnMasc = findViewById(R.id.radioBtnMasc);
        radioBtnFem = findViewById(R.id.radioBtnFem);
        numberTxtPeso = findViewById(R.id.txtNumberPeso);
        numberTxtIdade = findViewById(R.id.txtNumberIdade);
        numberTxtAltura = findViewById(R.id.txtNumberAltura);
        btnVisualizarPacientes = findViewById(R.id.btnResultado);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paciente paciente = new Paciente();
                paciente.setNome(txtNome.getText().toString());
                paciente.setAltura(Double.parseDouble(numberTxtAltura.getText().toString()));
                paciente.setIdade(Integer.parseInt(numberTxtIdade.getText().toString()));
                paciente.setPeso(Double.parseDouble(numberTxtPeso.getText().toString()));
                paciente.setSexo(radioBtnFem.isChecked() ? "Feminino" : "Masculino");
                pacientes.add(paciente);

                limparCampos();
            }
        });

        btnVisualizarPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qtdPacientes = pacientes.size();
                int qtdPacienteValidacaoIdade = 0;
                Paciente pacienteMaisVelho = new Paciente();

                for (Paciente item: pacientes) {
                    if (item.getIdade() >= 18 && item.getIdade() <= 25) {
                        qtdPacienteValidacaoIdade = qtdPacienteValidacaoIdade + 1;
                    }
                }

                if (pacientes.size() > 1) {
                    Collections.sort(pacientes);
                }

                pacienteMaisVelho = pacientes.get(pacientes.size() - 1);

                chamarAtividade(qtdPacientes, qtdPacienteValidacaoIdade, pacienteMaisVelho.getNome(), pacienteMaisVelho.getIdade());
            }
        });
    }

    private void chamarAtividade(int qtdPacientes, int qtdPacienteValidacaoIdade, String nomePacienteMaisVelho, int idadePacienteMaisVelho)
    {
        Bundle params = new Bundle();
        params.putString("qtdPacientes", String.valueOf(qtdPacientes));
        params.putString("qtdPacienteValidacaoIdade", String.valueOf(qtdPacienteValidacaoIdade));
        params.putString("nomePacienteMaisVelho", nomePacienteMaisVelho);
        params.putString("idadePacienteMaisVelho", String.valueOf(idadePacienteMaisVelho));

        Intent intent = new Intent(this, Resultado.class);
        intent.putExtras(params);

        startActivity(intent);
    }

    public void limparCampos() {
        txtNome.setText("");
        numberTxtAltura.setText("");
        numberTxtIdade.setText("");
        numberTxtPeso.setText("");
    }
}