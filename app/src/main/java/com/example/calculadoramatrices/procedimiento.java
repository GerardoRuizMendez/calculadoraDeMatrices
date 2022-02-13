package com.example.calculadoramatrices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class procedimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimiento);

        Bundle dato=getIntent().getExtras();
        matriz resultado = (matriz) dato.getSerializable("resultado");

        LinearLayout layout=findViewById(R.id.procedimiento);
        paso p=resultado.proc.poll();

        while(p!=null){
            if(p.tipo.equals("titulo")){
                TextView texto=new TextView(layout.getContext());
                texto.setText(p.contenido[0][0]);
                //texto.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                layout.addView(texto);
            }
            if(p.tipo.equals("matriz")){
                GridLayout layoutMatriz=new GridLayout(layout.getContext());
                layout.addView(layoutMatriz);
                matriz matriz1=new matriz(p.contenido.length, p.contenido[0].length);
                matriz1.creaMatriz(layoutMatriz);
                matriz1.dibujaResultado(p.contenido);
            }
            p=resultado.proc.poll();
        }
    }
}