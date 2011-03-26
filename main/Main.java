package main;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.File;
import org.eclipse.swt.*;
import sambalog.gui.Fenster1;

/**
 *@version 1.1
 * @author stefan
 */

public class Main {

    /**
     * Main  startet die Dateisuche und läd das Ergebnis in das Fenster.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f = new File("/sambalogs");
        dateiauslesen dl = new dateiauslesen(f);

        Fenster1 window1 = new Fenster1(dl.files);


        // TODO code application logic here
    }
}
