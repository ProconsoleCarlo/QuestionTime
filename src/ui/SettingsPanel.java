package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import qaCore.Settings;
import utils.GridBagBuilder;
/**
 * The dialog that contains the settings of the program
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class SettingsPanel extends JDialog{

	private static final long serialVersionUID = 1L;

	private GridBagBuilder builder = new GridBagBuilder(this);
	
	private Settings settings = Settings.getSettings();
	private boolean settingsModified = false;
	private JFrame mainFrame;
	/**
	 * Create a new dialog that contains the settings of the program
	 * @param mainFrame
	 */
	public SettingsPanel(JFrame mainFrame) {
		super(mainFrame);
		this.mainFrame = mainFrame;
		realize();
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Impostazioni");
		this.setResizable(false);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int)screenSize.getWidth()>>1)-((int)this.getPreferredSize().getWidth()>>1), 
							((int)screenSize.getHeight()>>1)-((int)this.getPreferredSize().getHeight()>>1));
		this.setVisible(true);
	}
	
	@Override
	public void dispose() {
		mainFrame.setEnabled(true);
		super.dispose();
	}
	
	private void realize() {
		
		final JCheckBox automaticNext = new JCheckBox("Avanzamento automatico");
		automaticNext.setSelected(settings.isAutomaticNext());
		automaticNext.setPreferredSize(new Dimension(settings.getButtonWidth()<<2, settings.getButtonHeight()>>1));
		automaticNext.setToolTipText("Le domande scorrono avanti automaticamente una volta selezionata la risposta, senza premere il bottone Successiva.");
		builder.add(automaticNext, 0, 0, 2, 1, 1, 0);
		
		final JCheckBox shuffle = new JCheckBox("Randomizza ordine domande");
		shuffle.setSelected(settings.isShuffle());
		shuffle.setPreferredSize(new Dimension(settings.getButtonWidth()<<2, settings.getButtonHeight()>>1));
		shuffle.setToolTipText("Le domande vengono poste in un ordine casuale che cambia di volta in volta");
		builder.add(shuffle, 0, 1, 2, 1, 1, 0);
		
		JButton cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsPanel.this.dispose();
			}
		});
		cancelButton.setPreferredSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()>>1));
		builder.add(cancelButton, 0, 2, 1, 1, 0, 0);
		
		JButton saveButton = new JButton("Salva");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (settings.isAutomaticNext() != automaticNext.isSelected()) {
					settings.setAutomaticNext(automaticNext.isSelected());
					settingsModified = true;
				}
				if (settings.isShuffle() != shuffle.isSelected()) {
					settings.setShuffle(shuffle.isSelected());
					JOptionPane.showMessageDialog(null, "Le modifiche effettuate verranno applicate nel prossimo test.");
					settingsModified = true;
				}
				if (settingsModified) {
					Settings.getSettings().updateSettings();
				}
				SettingsPanel.this.dispose();
			}
		});
		saveButton.setPreferredSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()>>1));
		builder.add(saveButton, 1, 2, 1, 1, 0, 0);	
	}
}
