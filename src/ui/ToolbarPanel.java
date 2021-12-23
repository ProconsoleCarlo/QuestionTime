package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import qaCore.QAManager;
import qaCore.Settings;
import utils.GridBagBuilder;
/**
 * The toolbar panel of the program
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class ToolbarPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private GridBagBuilder builder = new GridBagBuilder(this);
	
	private Settings settings = Settings.getSettings();
	private QAManager qaManager;
	
	private int buttonWidth = settings.getButtonWidth();
	private int buttonHeight = settings.getButtonHeight();
	
	private String[] availableTests;
	private JComboBox<String> chaptersComboBox;
	private JButton startTestButton;
	
	/**
	 * Create a new toolbar panel
	 * @param qaManager The manager of the program
	 */
	public ToolbarPanel(QAManager qaManager) {
		super();
		this.qaManager = qaManager;
		availableTests = qaManager.getAvailableTests();
		this.setBorder(new LineBorder(Color.black));
		realize();
	}
	
	public String getSelectedTest() {
		return (String)chaptersComboBox.getSelectedItem();
	}
	
	private void realize() {	
		chaptersComboBox = new JComboBox<>();
		chaptersComboBox.setPreferredSize(new Dimension(buttonWidth*3, buttonHeight));
		if (availableTests.length != 0) {
			for (int i = 0; i < availableTests.length; i++) {
				chaptersComboBox.addItem(availableTests[i]);
			}
		} else {
			chaptersComboBox.addItem("Nessun test disponibile");
			chaptersComboBox.setEnabled(false);
		}
		
		builder.add(chaptersComboBox, 0, 0, 1, 1, 0, 0);
		
		startTestButton = new JButton();
		File startImageIcon = new File("icons/start.png");
		if (startImageIcon.exists()) {
			startTestButton.setIcon(new ImageIcon(startImageIcon.toString()));
		}else {
			startTestButton.setText("Start");
		}
		startTestButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		startTestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (qaManager.isTestRunning()) {
						int userAns = JOptionPane.showConfirmDialog(ToolbarPanel.this, "Sei sicuro di volere interrompere il test?", "Domanda", JOptionPane.YES_NO_OPTION);
						if (userAns == JOptionPane.YES_OPTION) {
							qaManager.update();
						}
					} else {
						qaManager.update();
					}
				} catch (NullPointerException npe) {
					//Do nothing
				}
			}
		});
		builder.add(startTestButton, 1, 0, 1, 1, 0, 0);
		
		JButton settingsButton = new JButton();
		File settingsImageIcon = new File("icons/settings.png");
		if (settingsImageIcon.exists()) {
			settingsButton.setIcon(new ImageIcon(settingsImageIcon.toString()));
		}else {
			settingsButton.setText("Settings");
		}
		settingsButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ToolbarPanel.this.getRootPane().getParent().setEnabled(false);
				new SettingsPanel((JFrame)ToolbarPanel.this.getRootPane().getParent());
			}
		});
		builder.add(settingsButton, 2, 0, 1, 1, 0, 0);
		
		JButton infoButton = new JButton();
		File infoImageIcon = new File("icons/info.png");
		if (infoImageIcon.exists()) {
			infoButton.setIcon(new ImageIcon(infoImageIcon.toString()));
		}else {
			infoButton.setText("?");
		}
		infoButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		infoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InfoPanel();
			}
		});
		builder.add(infoButton, 3, 0, 1, 1, 0, 0);
		
		JLabel rightFiller = new JLabel();
		builder.add(rightFiller, 4, 0, 1, 1, 1, 0);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (qaManager.isTestRunning()) {
			chaptersComboBox.setEnabled(false);
			File stopImageIcon = new File("icons/stop.png");
			if (stopImageIcon.exists()) {
				startTestButton.setIcon(new ImageIcon(stopImageIcon.toString()));
			}else {
				startTestButton.setText("Stop");
			}
		} else {
			chaptersComboBox.setEnabled(true);
			startTestButton.setIcon(new ImageIcon("icons/start.png"));
		}
	}
}
