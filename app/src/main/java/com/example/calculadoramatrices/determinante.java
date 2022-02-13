package com.example.calculadoramatrices;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

public class determinante extends Fragment {
    matriz matriz1;

    public determinante() {
        // Required empty public constructor
    }

    public static determinante newInstance() {
        return new determinante();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_determinante, container, false);

        matriz1 =new matriz(3,3);
        matriz1.creaMatriz((GridLayout) v.findViewById(R.id.matriz1));

        Spinner tam = (Spinner) v.findViewById(R.id.largo);

        tam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) v.findViewById(R.id.textView3)).setText(adapterView.getSelectedItem().toString());
                matriz1.setFilas(i+1);
                matriz1.setColumnas(i+1);
                dibujar(v);
                //suma1.dibujaMatriz();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tam.setSelection(2);

        ((Button)v.findViewById(R.id.boton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operar(v);
            }
        });

        return v;
    }

    public void dibujar(View v){
        //suma1=new matriz(3,3);
        GridLayout layout=(GridLayout) v.findViewById(R.id.matriz1);
        layout.removeAllViews();
        matriz1.creaMatriz(layout);
        //suma1.dibujaMatriz();
    }

    public void operar(View view){
        /*matriz1.casillas[0][0].setText("0");
        matriz1.casillas[0][1].setText("4");
        matriz1.casillas[0][2].setText("-2");
        matriz1.casillas[0][3].setText("4");
        matriz1.casillas[1][0].setText("-6");
        matriz1.casillas[1][1].setText("2");
        matriz1.casillas[1][2].setText("10");
        matriz1.casillas[1][3].setText("0");
        matriz1.casillas[2][0].setText("5");
        matriz1.casillas[2][1].setText("8");
        matriz1.casillas[2][2].setText("-5");
        matriz1.casillas[2][3].setText("2");
        matriz1.casillas[3][0].setText("0");
        matriz1.casillas[3][1].setText("-2");
        matriz1.casillas[3][2].setText("1");
        matriz1.casillas[3][3].setText("0");*/
        //Determinante=392
        matriz1.actualizaValores();
        Intent i=new Intent(view.getContext(), resultado.class);


        //float arr[][]={{1,10},{1,1}};
        //matriz prueba=new matriz(arr);
        //resultado.valores[0][0]=prueba.determinante();


        i.putExtra("resultado", matriz1.determinante());
        startActivity(i);
    }
}