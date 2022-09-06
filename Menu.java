/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pescar;

public class Menu {

    private void inicio() {
        LT tec = new LT();
        String s;
        Ficheros fUs = null;
        char opc = ' ';
        while (opc != 's') {
            menu1();
            opc = tec.llegirCaracter();
            switch (opc) {
                case '1':
                    System.out.print("Quin es el teu nom? ");
                    // Pide un nombre de usuario y lo añade al registro.
                    // Crea un fichero de estadisticas para ese usuario nuevo.
                    s = tec.llegirLinia();
                    System.out.println();
                    fUs = new Ficheros("usuarios.txt", true);
                    if (!fUs.existeUs(s)) {
                        Ficheros F = new Ficheros("usuarios.txt", false);
                        Ficheros file = new Ficheros("estadistica " + s + ".txt");
                        F.alta(s);
                        F.cerrarFW();
                        file.crear();
                    } else {
                        System.out.println("L'usuari ja està donat d'alta.");
                    }
                    fUs.cerrarFR();
                    break;
                case '2':
                    System.out.print("Quin es el teu nom? ");
                    // Pide un nombre de usuario y se elimina del registro, así como
                    // sus estadisticas.
                    s = tec.llegirLinia();
                    System.out.println();
                    fUs = new Ficheros("usuarios.txt", true);
                    if (fUs.existeUs(s)) {
                        fUs.cerrarFR();
                        fUs = null;
                        Ficheros F = new Ficheros("usuarios.txt");
                        F.baja(s);
                        System.out.println("Usuari donat de baixa.");
                        Ficheros file = new Ficheros("estadistica " + s + ".txt");
                        file.eliminar();
                    } else {
                        System.out.println("L'usuari no existeix.");
                        fUs.cerrarFR();
                    }
                    break;
                case '3':
                    System.out.print("Quin es el teu nom? ");
                    s = tec.llegirLinia();
                    System.out.println();
                    fUs = new Ficheros("usuarios.txt", true);
                    //Comprobar que entra con un usuario que existe. Después crear un menú para elegir una zona de pesca. 
                    if (fUs.existeUs(s)) {
                        submenu2(s);
                    } else {
                        System.out.println("Has de donarte d'alta primer " + s);
                    }
                    fUs.cerrarFR();
                    break;
                case '4':
                    System.out.print("Quin es el teu nom? ");
                    s = tec.llegirLinia();
                    System.out.println("Aquestes son les estadistiques per usuari: ");
                    // Imprime registro usuario
                    Ficheros estUs = new Ficheros("estadistica " + s + ".txt", true);
                    if (estUs.fichVacio()) {
                        System.out.println("No hi ha estadistiques.");
                    } else {
                        estUs.printFich();
                    }
                    estUs.cerrarFR();
                    break;
                case '5':
                    System.out.println("Aquestes son les estadistiques globals: ");
                    // Imprime estadisticas globales
                    Ficheros estGlob = new Ficheros("estadistica global.txt", true);
                    estGlob.printFich();
                    estGlob.cerrarFR();
                    break;
                case 's':
                    System.out.println("Adeu!");
                    break;
                default:
                    System.out.println("Opció incorrecta.");
            }
        }
    }

    private void menu1() {
        System.out.println("***************************************");
        System.out.println("*   MENÚ PRINCIPAL DEL JOC DE PESCA   *");
        System.out.println("***************************************");
        System.out.println("*     1 Alta d'usuari                 *");
        System.out.println("*     2 Baixa d'usuari                *");
        System.out.println("*     3 Pesca en una pesquera         *");
        System.out.println("*     4 Estadístiques per usuari      *");
        System.out.println("*     5 Estadístiques globals         *");
        System.out.println("*     s Sortir                        *");
        System.out.println("***************************************");
        System.out.print("Opció? ");
    }

    private void submenu2(String usuario) {
        LT tec = new LT();
        String s = "";
        char opc2 = ' ';
        while (opc2 != 's') {
            menu2();
            opc2 = tec.llegirCaracter();
            switch (opc2) {
                case '1':
                    System.out.println("---ANEM A PESCAR AL MEDITERRANI---\n\n");
                    // Tenemos que hacer que lea los ficheros de la propia peesquera
                    s = "mediterraneo.txt";
                    submenu3(s, usuario);
                    break;
                case '2':
                    System.out.println("---ANEM A PESCAR A FLORIDA---\n\n");
                    s = "florida.txt";
                    submenu3(s, usuario);
                    break;
                case '3':
                    System.out.println("---ANEM A PESCAR AL CARIBE---\n\n");
                    s = "caribe.txt";
                    submenu3(s, usuario);
                    break;
                case '4':
                    System.out.println("---ANEM A PESCAR A LA CHINA MERIDIONAL---\n\n");
                    s = "china meridional.txt";
                    submenu3(s, usuario);
                    break;
                case '5':
                    System.out.println("---ANEM A PESCAR AL MAR ROJO---\n\n");
                    s = "mar rojo.txt";
                    submenu3(s, usuario);
                    break;
                case 's':
                    System.out.println("Tornam enrere.");
                    break;
                default:
                    System.out.println("Opció incorrecta.");
            }
        }
    }

    private void menu2() {
        System.out.println("***************************************");
        System.out.println("*          TRIA UNA PESQUERA          *");
        System.out.println("***************************************");
        System.out.println("*       1 Mediterrani                 *");
        System.out.println("*       2 Florida                     *");
        System.out.println("*       3 Caribe                      *");
        System.out.println("*       4 China meridional            *");
        System.out.println("*       5 Mar rojo                    *");
        System.out.println("*       s Sortir                      *");
        System.out.println("***************************************");
        System.out.print("Opció? ");

    }

    private void submenu3(String pesquera, String usuari) {
        LT tec = new LT();
        char opc3 = ' ';
        while (opc3 != 's') {
            menu3();
            opc3 = tec.llegirCaracter();
            switch (opc3) {
                case '1':
                    System.out.println("ANEM A PESCAR\n\n");
                    // Llamamos a la clase pescar
                    Pescar p = new Pescar();
                    p.pesca(pesquera, usuari);
                    break;
                case 's':
                    System.out.println("Tornam enrere.");
                    break;
                default:
                    System.out.println("Opció incorrecta.");
            }
        }
    }

    private void menu3() {
        System.out.println("***************************************");
        System.out.println("*           TRIA UNA OPCIÓ            *");
        System.out.println("***************************************");
        System.out.println("*       1 Pescar                      *");
        System.out.println("*       s No vull pescar més          *");
        System.out.println("***************************************");
        System.out.print("Opció? ");
    }

    public static void main(String[] args) {
        (new Menu()).inicio();
    }
}
