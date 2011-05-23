package gui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.dateiauslesen;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
/**
 *
 * @author stefan
 */
public class Fenster1 {

    private Button btnEnde, hallobtn;
    private List listedateien;
   public Text textfeld;
    private File[] dateinamen;
    private dateiauslesen bdaten;
    private BufferedReader breader;
   private ProgressBar anzeige;
   private MessageBox mbox3fail;

    /**
     *Lädt die Dateinamen
     * @param daten
     */
    public Fenster1(File[] daten) {
        dateinamen = daten;
        gui_anzeigen();
    }

    /**
     * Lädt die Dateiinhalte
     */
    public Fenster1() {
        dateinamen = null;
        gui_anzeigen();
    }

    /**
     * startet das Fenster
     */
    public final void gui_anzeigen() {


        final Display display = new Display();
        final Shell shell = new Shell(display);

        GridLayout gridlayout = new GridLayout(4, true);
        shell.setText("SambalogViewer V1");
        shell.setLayout(gridlayout);
        btnEnde = new Button(shell, SWT.PUSH);
        btnEnde.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        Label lblleer = new Label(shell,SWT.None);
        lblleer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
        btnEnde.setText("beenden");
        btnEnde.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                shell.close();
            }

        });
//hier kommt eine �nderung
        
        
        listedateien = new List(shell, SWT.SINGLE | SWT.V_SCROLL);
        listedateien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        if (dateinamen != null) {
            for (int i = 0; i < dateinamen.length; i++) {

                listedateien.add(dateinamen[i].toString());
            }
        } else {
            listedateien.add("Keine Dateien Vorhanden");
        }

        listedateien.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event e) {


                   display.asyncExec(new Runnable() {
    @Override
    public void run() {
        // Executed on UI Thread

            String[] selection = listedateien.getSelection();
                for (int i = 0; i < selection.length; i++) {
                    //   System.out.println(selection[i].toString());

                    File myf = new File(selection[i].toString());
                    bdaten = new dateiauslesen();

                    breader = bdaten.dateiladen(myf);
                   anzeige.setMaximum( (int) myf.length());
                }
                   //Threas der Shell lädt den text
                   display.asyncExec(new ladeinhalt(breader));


            }
        });
      }
});
            


        textfeld = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
        textfeld.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 2));

         anzeige = new ProgressBar(shell, SWT.HORIZONTAL);
        anzeige.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
        listedateien.pack();
        textfeld.pack();
        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();

    }

    /**
     *
     */
    public class ladeinhalt implements Runnable {

        private BufferedReader tbreader;
            public ladeinhalt(BufferedReader buffreader) {
            this.tbreader = buffreader;
        }

        public void run() {
             auslesen();
            //textfeld.setText(anzeigetext.getText());
           
        }// Ende run

        public synchronized void auslesen(){



             String s = "";
            try {


                while (tbreader.readLine() != null) {
                    String line;
                    while ((line = tbreader.readLine()) != null) {
                        s = s + line + "\n";
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(Fenster1.class.getName()).log(Level.SEVERE, null, ex);
            }
         
          textfeld.setText(s);

        }


    } //Ende ladeinhalt
}
