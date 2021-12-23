package tests;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import qaCore.QAManager;
import qaCore.QuestionsInterpreter;
import qaCore.Settings;
import ui.MainPanel;
/**
 * The main program
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: so' cazzi!
		}
		Settings.getSettings().initialize();
		QAManager qaManager = new QAManager(new QuestionsInterpreter());
		JFrame frame = new JFrame();
		MainPanel mainPanel = new MainPanel(qaManager);
		qaManager.addObserver(mainPanel);
		frame.setFocusable(true);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setTitle("QuestionTime");
		frame.setMinimumSize(frame.getPreferredSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, (int)screenSize.getWidth()>>1, (int)screenSize.getHeight()>>1);
		frame.setVisible(true);
	}
}
