package com.example.calculadoramatrices;

import java.io.Serializable;

public class paso implements Serializable {
    public String tipo;
    public String[][] contenido;

    public paso(String tipo, String[][] contenido){
        this.tipo=tipo;
        this.contenido=contenido;
    }
}
