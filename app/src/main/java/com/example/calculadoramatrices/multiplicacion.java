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

public class multiplicacion extends Fragment {
    matriz matriz1;
    float coeficiente=0;

    public multiplicacion() {
        // Required empty public constructor
    }

    public static multiplicacion newInstance() {
        return new multiplicacion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_multiplicacion, container, false);
        matriz1 =new matriz(3,3);
        matriz1.creaMatriz((GridLayout) v.findViewById(R.id.matriz1));

        Spinner largo = (Spinner) v.findViewById(R.id.largo);
        Spinner ancho = (Spinner) v.findViewById(R.id.ancho);

        largo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) v.findViewById(R.id.textView3)).setText(adapterView.getSelectedItem().toString());
                matriz1.setFilas(i+1);
                dibujar(v);
                //suma1.dibujaMatriz();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ancho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) v.findViewById(R.id.textView3)).setText(adapterView.getSelectedItem().toString());
                matriz1.setColumnas(i+1);

                dibujar(v);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        largo.setSelection(2);
        ancho.setSelection(2);

        ((EditText) v.findViewById(R.id.coeficiente)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(String.valueOf(editable).equals("") || String.valueOf(editable).equals("-") || String.valueOf(editable).endsWith(".")){
                    coeficiente=0;
                    return;
                }
                coeficiente=Float.parseFloat(String.valueOf(editable));
            }
        });

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
        matriz1.actualizaValores();
        Intent i=new Intent(view.getContext(), resultado.class);
        i.putExtra("resultado", matriz1.multiplicacion(coeficiente));
        startActivity(i);
    }
}