package com.example.calculadoramatrices;

import android.text.InputType;
import android.widget.EditText;
import android.widget.GridLayout;

import java.io.Serializable;

public class matriz2 implements Serializable { //No funciono la version 2 XD
    private int filas;
    private int columnas;
    EditText casillas[][];
    String proc[][][];

    public matriz2(int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
        casillas=new EditText[filas][columnas];
    }
    public void creaMatriz(GridLayout layout){
        layout.setRowCount(filas);
        layout.setColumnCount(columnas);

        //valores=new float[filas][columnas];
        casillas=new EditText[filas][columnas];

        for(int i=0; i<casillas.length; i++){
            for(int j=0; j<casillas[i].length; j++){

                casillas[i][j]=new EditText(layout.getContext());
                casillas[i][j].setWidth(100);
                casillas[i][j].setBackgroundResource(R.drawable.border);
                casillas[i][j].setText("1");
                casillas[i][j].setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                casillas[i][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);


                layout.addView(casillas[i][j]);
            }
        }
    }

    /*public void dibujaMatriz(){
        for(int i=0; i<casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j].setText(valores[i][j] +"");
            }
        }
    }*/

    public matriz2 suma(matriz2 m){
        float v1, v2;
        matriz2 resultado=new matriz2(filas, columnas);
        for(int i=0; i<casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                v1=Float.parseFloat(String.valueOf(casillas[i][j].getText()));
                v2=Float.parseFloat(String.valueOf(m.casillas[i][j].getText()));
                resultado.casillas[i][j].setText("" +(v1+v2));
            }
        }
        return resultado;
    }
    /*
    public matriz resta(matriz m){
        matriz resultado=new matriz(filas, columnas);
        for(int i=0; i<valores.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {
                resultado.valores[i][j]=valores[i][j]-m.valores[i][j];
            }
        }
        return resultado;
    }

    public matriz multiplicacion(float n){
        matriz resultado=new matriz(filas, columnas);
        for(int i=0; i<valores.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {
                resultado.valores[i][j]=valores[i][j]*n;
            }
        }
        return resultado;
    }

    public void actualizaValores(){
        for(int i=0; i<casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                valores[i][j]=Float.parseFloat(casillas[i][j].getText().toString());
            }
        }
    }*/

    public void setFilas(int filas) {
        this.filas = filas;
        casillas=new EditText[filas][columnas];
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
        casillas=new EditText[filas][columnas];
    }
}
