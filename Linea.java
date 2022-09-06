package pescar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Joan Martorell & Emilio
 */
public class Linea {

    private static final int MAX = 100;
    private int n;
    private char[] linea;

    public Linea() {
        n = 0;
        linea = new char[MAX];
    }

    //Carga el String en un objeto linea. Si el string es null, se genera una
    //linea con -1 caracteres. Esto se usará para identificar el final de fichero.
    public void cargarLinea(String s) {
        if (s != null) {
            linea = s.toCharArray();
            n = linea.length;
        } else {
            linea = null;
            n = -1;
        }
    }

    //Comprueba si dos lineas son iguales
    public static boolean equals(Linea a, Linea b) {
        boolean equals = false;
        if (a.getN() == b.getN()) {
            equals = true;
            for (int i = 0; i < a.getN(); i++) {
                if (a.getChar(i) != b.getChar(i)) {
                    equals = false;
                }
            }
        }
        return equals;
    }

    //Metodo que convierte una linea en un array de objetos Palabra. Util para
    //gestionar las palabras individualmente.
    public Paraula[] toParaules(int numPals) {
        Paraula[] pals = new Paraula[numPals];
        int pos = 0;
        int j = 0;
        while (pos < numPals) {
            pals[pos] = new Paraula();
            for (int i = j; (i < n) && (linea[i] != '#'); i++) {
                pals[pos].afegirCaracter(linea[i]);
                j++;
            }
            j++;
            pos++;
        }
        return pals;
    }

    @Override
    public String toString() {
        String s = "";
        if (n == -1) {
            s = null;
        }
        for (int i = 0; i < n; i++) {
            s = s + linea[i];
        }
        return s;
    }

    public int getN() {
        return n;
    }

    public char getChar(int i) {
        return linea[i];
    }
}
