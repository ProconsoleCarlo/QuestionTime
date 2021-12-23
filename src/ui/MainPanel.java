package ui;

import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import qaCore.QAManager;
import qaCore.QuestionAndAnswers;
import utils.GridBagBuilder;
/**
 * The main panel of the program
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class MainPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private GridBagBuilder builder = new GridBagBuilder(this);
	
	private QAManager qaManager;
	
	private static int width = 640;
	private static int height = 480;
	
	private TestPanel testPanel;
	private ToolbarPanel toolbarPanel;
	private ResultsPanel resultsPanel;
	
	/**
	 * Create the main panel of the program
	 * @param qaManager The manager of the program
	 */
	public MainPanel(QAManager qaManager) {
		super();
		this.qaManager = qaManager;
		realize();
	}
	
	private void realize() {
		toolbarPanel = new ToolbarPanel(qaManager);
		builder.add(toolbarPanel, 0, 0, 1, 1, 1, 0);
		int toolbarHeight = (int)toolbarPanel.getPreferredSize().getHeight();
		
		testPanel = new TestPanel(qaManager);
		testPanel.setPreferredSize(new Dimension(width, height-toolbarHeight));
		testPanel.setVisible(true);
		builder.add(testPanel, 0, 1, 1, 1, 1, 1);
		
		resultsPanel = new ResultsPanel(qaManager);
		resultsPanel.setPreferredSize(new Dimension(width, height-toolbarHeight));
		resultsPanel.setVisible(false);
		builder.add(resultsPanel, 0, 1, 1, 1, 1, 1);
		
		qaManager.addObserver(toolbarPanel);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (qaManager.isTestRunning()) {
			qaManager.terminateTest();
			testPanel.setVisible(false);
			resultsPanel.setVisible(true);	
		} else {
			List<QuestionAndAnswers> questions = qaManager.createTest(toolbarPanel.getSelectedTest());
			if (questions.size() != 0) {
				testPanel.createTest(questions);
				resultsPanel.setVisible(false);
				testPanel.setVisible(true);
			}
		}
	}
}
