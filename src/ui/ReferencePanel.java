package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * The dialog that contains the reference image of the actual question
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class ReferencePanel extends JDialog{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new dialog that contains the reference image of the actual question
	 */
	public ReferencePanel(BufferedImage referenceImage) {
		super(new JFrame());
		realize(referenceImage);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Immagine di riferimento");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)screenSize.getWidth()>>1, 0,	(int)screenSize.getWidth()>>1, (int)screenSize.getHeight()>>1);
		this.setVisible(true);
	}

	private void realize(BufferedImage referenceImage) {		
		JLabel imageReference = new JLabel(new ImageIcon(referenceImage));
		imageReference.setToolTipText("Click per chiudere");
		imageReference.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				ReferencePanel.this.dispose();
			}
		});
		this.add(imageReference);
	}
}
