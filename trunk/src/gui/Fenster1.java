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
   private MessageBox mbox3fail, jokebox1, jokebox2, jokebox3, jokebox4, jokebox5, jokebox6, jokebox7, jokebox8, jokebox9;

    /**
     *LÃ¤dt die Dateinamen
     * @param daten
     */
    public Fenster1(File[] daten) {
        dateinamen = daten;
        gui_anzeigen();
    }

    /**
     * LÃ¤dt die Dateiinhalte
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
//hier kommt eine änderung
        
      //änderung by Patrick M. XD
        hallobtn = new Button(shell, SWT.PUSH);
        hallobtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        hallobtn.setText("Eine Änderung");
        hallobtn.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				
				mbox3fail = new MessageBox(shell, SWT.OK| SWT.ICON_INFORMATION);
				mbox3fail.setText("Ja es geht");
				mbox3fail.setMessage("Hallo Hörsaal");
				mbox3fail.open();
				
				if(mbox3fail != null)
		        {
		        	jokebox1 = new MessageBox(shell, SWT.OK | SWT.ICON_QUESTION);
		        	jokebox1.setText("SCHADE");
		        	jokebox1.setMessage("Willst du wirklich schließen?");
		        	jokebox1.open();
		        
					jokebox2 = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
		        	jokebox2.setText("HAHA");
		        	jokebox2.setMessage("Du hast das Zauberwort nicht gesagt");
		        	jokebox2.open();
				
					jokebox3 = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
		        	jokebox3.setText(":-(");
		        	jokebox3.setMessage("Ach bitte geh nicht");
		        	jokebox3.open();
				
					jokebox4 = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
		        	jokebox4.setText("Willst du schluss machen");
		        	jokebox4.setMessage("Drück ma weiter");
		        	jokebox4.open();
				
					jokebox5 = new MessageBox(shell, SWT.OK | SWT.ICON_WORKING);
		        	jokebox5.setText("HAHA");
		        	jokebox5.setMessage("Klick mich");
		        	jokebox5.open();
				
					jokebox6 = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
		        	jokebox6.setText("oO");
		        	jokebox6.setMessage("Willst du im Hörsaal Fragen wie das geht");
		        	jokebox6.open();
				
					jokebox7 = new MessageBox(shell, SWT.OK | SWT.ICON_WORKING);
		        	jokebox7.setText("RTFM");
		        	jokebox7.setMessage("Oder willst du die Proffs fragen");
		        	jokebox7.open();
		        	
		        	jokebox8 = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
		        	jokebox8.setText("Mano");
		        	jokebox8.setMessage("Na gut dann nicht");
		        	jokebox8.open();
		        	
		        	jokebox9 = new MessageBox(shell, SWT.OK | SWT.ICON_CANCEL);
		        	jokebox9.setText("CU GL HF");
		        	jokebox9.setMessage("OK machmer Schluss. Klick mich");
		        	jokebox9.open();
				}
				
				
			}
			
		});
        
        
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
                   //Threas der Shell lÃ¤dt den text
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
