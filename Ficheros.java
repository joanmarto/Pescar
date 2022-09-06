/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pescar;

import java.io.*;

public class Ficheros {

    private File f = null;
    private String name;
    private FileReader fr = null;
    private FileWriter fw = null;
    private BufferedReader br = null;
    private BufferedWriter bw = null;

    //Objeto Fichero FileReader/FileWriter en función del parametro lec
    public Ficheros(String nombre, boolean lec) {
        name = nombre;
        try {
            if (lec) {
                fr = new FileReader(name);
                br = new BufferedReader(fr);
            } else {
                fw = new FileWriter(name, true);
                bw = new BufferedWriter(fw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Objeto Fichero File
    public Ficheros(String nombre) {
        name = nombre;
        try {
            f = new File(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Crea un nuevo fichero con el nombre pasado por parametro al constructor
    public void crear() {
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Elimina un fichero
    public void eliminar() {
        try {
            f.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Cambia el nombre a un fichero
    public void renombrarA(File aux) {
        try {
            f.renameTo(aux);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para leer una linea de un fichero
    public String leerLinea() {
        Linea aux = new Linea();
        try {
            aux.cargarLinea(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux.toString();
    }

    //Cierra un FileWriter
    public void cerrarFW() {
        try {
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Cierrra un FileReader
    public void cerrarFR() {
        try {
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Escribe en el fichero de texto
    public void alta(String s) {
        try {
            bw.write(s);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Da de baja un usuario
    public void baja(String s) {
        Linea L1 = new Linea();
        Linea L2 = new Linea();
        Ficheros auxF = new Ficheros("copia.txt");
        auxF.crear();
        Ficheros auxw = new Ficheros("copia.txt", false);
        Ficheros auxr = new Ficheros("usuarios.txt", true);
        //Crea un fichero auxiliar para copiar todos los usuarios distintos al
        //pasado por parametro. Después elimina el fichero usuarios.txt y renombra
        //la copia.
        L1.cargarLinea(s);
        L2.cargarLinea(auxr.leerLinea());
        while (L2.getN() != -1) {
            if (!Linea.equals(L1, L2)) {    //Se comprueba si los nombres son iguales
                auxw.alta(L2.toString());
            }
            L2.cargarLinea(auxr.leerLinea());
        }
        auxw.cerrarFW();
        auxr.cerrarFR();
        eliminar();
        auxF.renombrarA(new File("usuarios.txt"));
    }

    //Comprueba si un fichero está vacio.
    public boolean fichVacio() {
        boolean vacio = true;
        try {
            FileReader faux = new FileReader(name);
            BufferedReader baux = new BufferedReader(faux);
            if (baux.readLine() != null) {
                vacio = false;
            }
            baux.close();
            faux.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacio;
    }

    //Comprueba si el usuario pasado por paramtro está dado de alta
    public boolean existeUs(String n) {
        Linea l1 = new Linea();
        Linea l2 = new Linea();
        boolean existe = false;
        l1.cargarLinea(n);
        l2.cargarLinea(leerLinea());
        while (l2.getN() != -1) {
            if (Linea.equals(l1, l2)) {
                existe = true;
            }
            l2.cargarLinea(leerLinea());
        }
        return existe;
    }

    //Imprime un fichero entero
    public void printFich() {
        Linea L = new Linea();
        try {
            L.cargarLinea(br.readLine());
            while (L.getN() != -1) {
                System.out.println(L);
                L.cargarLinea(br.readLine());
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
