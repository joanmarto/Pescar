/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pescar;

import java.io.File;
import java.util.Random;

public class Pescar {

    //Output: numero random usad para calcular el pez
    public double peix() {
        Random r = new Random();
        double numero;
        numero = r.nextDouble();
        return numero;
    }

    //Metodo para calcular el peso del pez pescado.
    //Los parametros son las palabras extraidas del fichero de pesquerías que 
    //indican el peso minimo y maximo del pez.
    public double peso(Paraula a, Paraula b) {
        Random r = new Random();
        double numero;
        numero = r.nextDouble();
        double max = Double.parseDouble(b.toString());
        double min = Double.parseDouble(a.toString());
        double media = (max - min);
        double peso = (media * numero) + min;
        return peso;
    }

    //Los parametros son el nombre de la pesquería y el nombre de usuario
    public void pesca(String s, String us) {
        Ficheros f = new Ficheros(s, true);
        Linea l = new Linea();
        Paraula[] pals = null;
        double d = peix();
        l.cargarLinea(f.leerLinea());
        while (l.getN() != -1) {
            pals = l.toParaules(4); //Convierte una linea en un array de obetos Palabra
            String a = pals[1].toString();
            double fa = Double.parseDouble(a);
            if (fa > d) {   //Si el pocentaje del fichero es mayor que el porcentaje
                break;      //dado, se sale del bucle.
            }
            l.cargarLinea(f.leerLinea());
        }
        double p = peso(pals[2], pals[3]);
        System.out.println("Has pescat un " + pals[0] + " que pesa: " + p + "Kg.");
        guardar(us, pals[0], p);
        f.cerrarFR();
    }

    public void guardar(String us, Paraula pez, double peso) {
        //Guardar registros en estadisticas de usuario
        Ficheros f = new Ficheros("estadistica " + us + ".txt", false);
        String registro = pez.toString() + '#' + peso;
        f.alta(registro);
        f.cerrarFW();
        //Guardar registros en estadisticas globales
        Linea l = new Linea();
        Paraula[] pals = null;
        Ficheros eGlob = new Ficheros("estadistica global.txt");
        Ficheros copia = new Ficheros("copia.txt");
        copia.crear();
        Ficheros reader = new Ficheros("estadistica global.txt", true);
        Ficheros writer = new Ficheros("copia.txt", false);
        //Se copian todas las estadisticas en un fichero auxiliar, y se comprueba 
        //que registro es mejor para guardarlo. Después se elimina el fichero de
        //estadisticas y se renombra el auxiliar.
        l.cargarLinea(reader.leerLinea());
        while (l.getN() != -1) {
            pals = l.toParaules(3);
            if (Paraula.equals(pals[0], pez)) {
                if (peso > Double.parseDouble(pals[2].toString())) {
                    l.cargarLinea(pals[0].toString() + "#" + us + "#" + peso + "#");
                }
                writer.alta(l.toString());
            } else {
                writer.alta(l.toString());
            }
            l.cargarLinea(reader.leerLinea());
        }
        writer.cerrarFW();
        reader.cerrarFR();
        eGlob.eliminar();
        copia.renombrarA(new File("estadistica global.txt"));
    }
}
