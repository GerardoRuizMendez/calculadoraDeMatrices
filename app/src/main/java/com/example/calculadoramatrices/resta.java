package com.example.calculadoramatrices;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;

public class resta extends Fragment {
    matriz matriz1, matriz2;

    public resta() {
        // Required empty public constructor
    }

    public static resta newInstance() {
        return new resta();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_resta, container, false);
        matriz1=new matriz(3,3);
        matriz2 =new matriz(3,3);

        matriz1.creaMatriz((GridLayout) v.findViewById(R.id.matriz1)); //crea la matriz en el GridLayout
        matriz2.creaMatriz((GridLayout) v.findViewById(R.id.matriz2));

        /*((Button)v.findViewById(R.id.boton)).setOnClickListener(new View.OnClickListener() { //PRUEBA PARA CAMBIAR TAMAÑO
            @Override
            public void onClick(View view) {
                prueba(v);
            }
        });*/
        Spinner largo = (Spinner) v.findViewById(R.id.largo);
        Spinner ancho = (Spinner) v.findViewById(R.id.ancho);
        largo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) v.findViewById(R.id.textView3)).setText(adapterView.getSelectedItem().toString());
                matriz1.setFilas(i+1);
                matriz2.setFilas(i+1);
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
                matriz2.setColumnas(i+1);

                dibujar(v);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        largo.setSelection(2);
        ancho.setSelection(2);

        Button boton=(Button) v.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
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

        layout=(GridLayout) v.findViewById(R.id.matriz2);
        layout.removeAllViews();
        matriz2.creaMatriz(layout);

    }

    public void operar(View view){
        matriz1.actualizaValores();
        matriz2.actualizaValores();
        Intent i=new Intent(view.getContext(), resultado.class);
        i.putExtra("resultado", matriz1.resta(matriz2));
        startActivity(i);
    }
}