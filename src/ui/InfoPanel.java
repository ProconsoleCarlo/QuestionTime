package ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * The panel that contains the informations of the program
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class InfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static File guideFile = new File("Guida.pdf");
	
	public InfoPanel() {
		super();
		realize();
		this.setVisible(true);
	}

	private void realize() {
		String crediti = "<html>" +
							"Questo programma e' stato realizzato da:" +
							"<br />" +
							"<br />"+
							"<p>"+
								"Carlo Bobba" +								
							"</p>" +
							"<br />" +
							"Grazie per averlo utilizzato! :)" +
							"<br />" +
							"<br />" +
							"La guida è disponibile nella forma di suggerimenti nel file " + guideFile +
						"</html>";
		
		Object[] options = {"Guida", "Ok"};
		int n = JOptionPane.showOptionDialog(this, crediti, "Informazioni sul programma",
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, 
				null, options, options[1]);
		if (n == JOptionPane.YES_OPTION) {
			try {
				Desktop.getDesktop().open(guideFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
