package com.example.calculadoramatrices;

import android.text.InputType;
import android.widget.EditText;
import android.widget.GridLayout;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class matriz implements Serializable {

    private int filas;
    private int columnas;
    EditText casillas[][];
    float valores[][];
    Queue<paso> proc;
    /*nota: tenemos que crear una matriz para los valores independiente de la matriz de los
    EditText ya que, al momento de ser creadas las matrices resultados, no podemos
    guardar los valores en el propio EditText ya que no contaremos con el GridLayout hasta mas
    adelante*/
    public matriz(int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
        valores=new float[filas][columnas];
        casillas=new EditText[filas][columnas];

        proc=new LinkedList<paso>();
    }
    public matriz(float[][] valores){
        this.filas=valores.length;
        this.columnas=valores[0].length;
        this.valores=valores;
        casillas=new EditText[filas][columnas];
        proc=new LinkedList<paso>();
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
                casillas[i][j].setText(""); //USAR PARA PRUEBAS
                casillas[i][j].setHint("0");
                casillas[i][j].setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                casillas[i][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);


                layout.addView(casillas[i][j]);
            }
        }
    }

    public void dibujaMatriz(){
        String texto;
        for(int i=0; i<casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                texto=valores[i][j] +"";
                if(texto.endsWith(".0"))texto=texto.substring(0,texto.length()-2);
                casillas[i][j].setText(texto);
            }
        }
    }

    public void dibujaResultado(String [][] m){
        for(int i=0; i<casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j].setText(m[i][j] +"");
            }
        }
    }

    public void actualizaValores(){
        for(int i=0; i<casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if(casillas[i][j].getText().toString().equals("")){
                    valores[i][j]=0;
                }
                else{
                    valores[i][j]=Float.parseFloat(casillas[i][j].getText().toString());
                }

            }
        }
    }

    public matriz suma(matriz m){
        matriz resultado=new matriz(filas, columnas);
        resultado.proc.offer(new paso("titulo", new String[][]{{"Sumamos los números de cada fila y cada columna"}}));

        String[][] frase=new String[filas][columnas];
        for(int i=0; i<valores.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {
                resultado.valores[i][j]=valores[i][j]+m.valores[i][j];
                frase[i][j]= redu(valores[i][j])+ "+" + redu(m.valores[i][j]) +"=" + redu((valores[i][j]+m.valores[i][j]));
            }
        }
        resultado.proc.offer(new paso("matriz", frase));

        return resultado;
    }

    public matriz resta(matriz m){
        matriz resultado=new matriz(filas, columnas);
        resultado.proc.offer(new paso("titulo", new String[][]{{"Restamos los números de cada fila y cada columna"}}));

        String[][] frase=new String[filas][columnas];
        for(int i=0; i<valores.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {
                resultado.valores[i][j]=valores[i][j]-m.valores[i][j];
                frase[i][j]=redu(valores[i][j])+ "-" + redu(m.valores[i][j]) +"=" + redu((valores[i][j]-m.valores[i][j]));
            }
        }
        resultado.proc.offer(new paso("matriz", frase));

        return resultado;
    }

    public matriz multiplicacion(float n){
        matriz resultado=new matriz(filas, columnas);
        resultado.proc.offer(new paso("titulo", new String[][]{{"Multiplicamos cada elemento por el coeficiente dado"}}));

        String[][] frase=new String[filas][columnas];
        for(int i=0; i<valores.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {
                resultado.valores[i][j]=valores[i][j]*n;
                frase[i][j]= redu(valores[i][j])+ "x" +redu(n) +" = " + redu((valores[i][j]*n));
            }
        }
        resultado.proc.offer(new paso("matriz", frase));

        return resultado;
    }

    public matriz multiplicacion(matriz m){
        matriz resultado=new matriz(filas, m.columnas);

        resultado.proc.offer(new paso("titulo", new String[][]{{"Filas de la primera matriz: "+this.columnas}}));
        resultado.proc.offer(new paso("titulo", new String[][]{{"Columnas de la segunda matriz: "+m.filas}}));
        resultado.proc.offer(new paso("titulo", new String[][]{{"Al ser iguales, procedemos a multiplicar"}}));
        resultado.proc.offer(new paso("titulo", new String[][]{{""}}));

        String frase;
        String calculo;
        for(int i=0; i<filas; i++) {
            for (int j = 0; j < m.columnas; j++) {
                frase="c" +(i+1) +(j+1) +" = "; //c11 = a11*b11 + a12*b21 + a13*b31
                calculo="      ";
                for (int k=0; k<columnas; k++){    //  1              1
                    frase+="a" +(i+1) +(k+1) +"*" +"b" +(k+1) +(j+1) +" + ";
                    calculo+=valores[i][k] +"*" +m.valores[k][j] + " + ";
                    resultado.valores[i][j]+=valores[i][k]*m.valores[k][j];
                }
                frase=frase.substring(0, frase.length()-3);
                calculo=calculo.substring(0, frase.length()-3);
                resultado.proc.offer(new paso("titulo", new String[][]{{frase}}));
                resultado.proc.offer(new paso("titulo", new String[][]{{calculo}}));
            }
        }
        return resultado;
    }

    public matriz determinante(){
        matriz resultado=new matriz(1,1);
        if(filas==1) return new matriz(new float[][]{{valores[0][0]}});
        String frase="";

        for(int i=0; i<filas; i++){
            frase+="a1" +(i+1) +"*A1" +(i+1) +" + ";
        }
        frase=frase.substring(0, frase.length()-3);
        resultado.proc.offer(new paso("titulo", new String[][]{{frase}}));
        float det=0;
        float[] adjuntas=new float[filas];


        for(int i=0; i<filas; i++) {
            adjuntas[i]=cofactor(0,i);
            det+=valores[0][i]*adjuntas[i];
        }
        resultado.valores[0][0]=det;
        return resultado;
    }

    public float cofactor(int fila, int columna){
        int ii, jj;
        float arr[][]=new float[filas-1][filas-1];
        ii=0; jj=0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {

                if (!(i == fila || j == columna)) {
                    arr[ii][jj] = valores[i][j];
                    jj++;
                    if(jj==filas-1)ii++;
                }

            }
            jj=0;

        }

        matriz resultado=new matriz(arr);
        return (float) (resultado.determinante().valores[0][0]*Math.pow(-1,fila+columna));
    }

    public matriz matrizAdjunta(){
        matriz resultado=new matriz(filas, columnas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado.valores[i][j]=cofactor(i,j);
            }
        }
        return resultado;
    }

    public matriz matrizTranspuesta(){
        matriz resultado=new matriz(filas, columnas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado.valores[i][j]=valores[j][i];
            }
        }
        return resultado;
    }

    public matriz matrizInversa(){
        /*
        float determinante=1/matriz1.determinante();
        matriz resultado=matriz1.matrizTranspuesta();
        resultado=resultado.matrizAdjunta();
        resultado=resultado.multiplicacion(determinante);
        * */
        float determinante=1/this.determinante().valores[0][0];
        if(determinante==0){
            float[][] resultado={{0.0f}};
            return new matriz(resultado);
        }
        matriz resultado=this.matrizTranspuesta();
        resultado=resultado.matrizAdjunta();
        resultado=resultado.multiplicacion(determinante);

        return resultado;
    }

    public void setFilas(int filas) {
        this.filas = filas;
        valores=new float[filas][columnas];
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
        valores=new float[filas][columnas];
    }

    private String redu(float n){ //quita el punto decimal de un numero si es .0
        String texto=n +"";
        if(texto.endsWith(".0"))texto=texto.substring(0,texto.length()-2);
        return texto;
    }

    public String[][] toStringArray(){
        String[][] array=new String[filas][columnas];
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                array[i][j]=valores[i][j] +"";
            }
        }

        return array;
    }

}
