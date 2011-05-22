/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import gui.Fenster1;

/**
 *
 * @author stefan
 */
public class dateiauslesen {

    private File meinDir;
    File[] files;
  
/**
 * 
 * @param mydir
 */
    public dateiauslesen(File mydir) {
        meinDir = mydir;
        listDir(meinDir);

    }
/**
 * 
 */
    public dateiauslesen() {
        
    }
/**
 * 
 * @param dir
 * @return this.files
 */
    public File[] listDir(File dir) {

        files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                //System.out.print(files[i].getAbsolutePath());
                if (files[i].isDirectory()) {
                   // System.out.print(" (Ordner)\n");

                    listDir(files[i]);

                } else {
                    //System.out.print(" (Datei)\n");
                }

            }

        }
        return this.files;
    }
/**
 * Lädt Dateinhalt und gibt es an Fenster1 weiter.
 * @param ladedatei
 * @return breader
 */
    public BufferedReader dateiladen(File ladedatei){
      BufferedReader breader = null;
        try{
            FileReader fr = new FileReader(ladedatei);
        breader = new  BufferedReader(fr);
        }catch(Exception ex){

        }
        return breader;
    }

}
