/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pescar;

/**
 *
 * @author mascport
 */
public class Paraula {

    //Clase palabra ofrecida por el profesor. Ligeramente modificada.
    private final int maxinicial = 20;
    private final int tramo = 10;
    private char[] pal;
    private int n;

    public Paraula() {
        pal = new char[maxinicial];
        n = 0;
    }

    public void afegirCaracter(char c) {
        if (n < pal.length) {
            pal[n++] = c;
        } else {
            char aux[] = new char[n + tramo];
            for (int i = 0; i < n; i++) {
                aux[i] = pal[i];
            }
            aux[n++] = c;
            pal = aux;
        }
    }

    //Metodo booleano que devuelve true si dos objetos Palabra son iguales
    public static boolean equals(Paraula a, Paraula b) {
        boolean equals = false;
        int pos = 0;
        if (a.getNum() == b.getNum()) {
            equals = true;
            while (pos < a.getNum()) {
                if (a.getLletra(pos) != b.getLletra(pos)) {
                    equals = false;
                }
                pos++;
            }
        }
        return equals;
    }

    public boolean buida() {
        return (n == 0);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < n; i++) {
            res = res + pal[i];
        }
        return res;
    }

    public char getLletra(int i) {
        return pal[i];
    }

    public int getNum() {
        return n;
    }
}
