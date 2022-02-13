package com.example.calculadoramatrices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Bundle dato=getIntent().getExtras();
        matriz resultado = (matriz) dato.getSerializable("resultado");

        resultado.creaMatriz((GridLayout)findViewById(R.id.resultado));
        resultado.dibujaMatriz();
        resultado.actualizaValores();
    }

    public void boton(View view){
        Bundle dato=getIntent().getExtras();
        matriz resultado = (matriz) dato.getSerializable("resultado");
        Intent i=new Intent(view.getContext(), procedimiento.class);
        i.putExtra("resultado", resultado);
        startActivity(i);
    }

}

