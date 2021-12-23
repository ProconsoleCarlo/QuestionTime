package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import qaCore.QAManager;
import qaCore.Settings;
import utils.GridBagBuilder;
/**
 * The panel that show the results of the test
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class ResultsPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private GridBagBuilder builder = new GridBagBuilder(this);
	
	private Settings settings = Settings.getSettings();
	private QAManager qaManager;
	
	private JLabel correctAnswersLabel;
	private JLabel blankAnswersLabel;
	private JLabel errorsAnswersLabel;
	private ButtonGroup shownResultGroup;
	private JTextArea resultsArea = new JTextArea();;

	/**
	 * Create a new panel that contains the results
	 * @param qaManager The manager of the program
	 */
	public ResultsPanel(QAManager qaManager) {
		super();
		this.qaManager = qaManager;
		realize();
	}
	
	private void realize() {
		JLabel resultLabel = new JLabel("Risultati:");
		builder.add(resultLabel, 0, 0, 1, 1, 1, 0);
		
		JLabel shownResultsLabel = new JLabel("Visualizza:");
		builder.add(shownResultsLabel, 1, 0, 1, 1, 1, 0);
		
		shownResultGroup = new ButtonGroup();
		String[] showActions = {settings.getCorrectResultType(), settings.getBlankResultType(), settings.getErrorResultType()};
		for (int i = 0; i < 3; i++) {
			final JCheckBox showCheckBox = new JCheckBox();
			shownResultGroup.add(showCheckBox);
			showCheckBox.setActionCommand(showActions[i]);	
			showCheckBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					resultsArea.setText(qaManager.getResults().getResult(showCheckBox.getActionCommand()));
				}
			});
			builder.add(showCheckBox, 1, i+1, 1, 1, 1, 0);	
		}
		
		correctAnswersLabel = new JLabel();
		builder.add(correctAnswersLabel, 0, 1, 1, 1, 1, 0);
		
		blankAnswersLabel = new JLabel();
		builder.add(blankAnswersLabel, 0, 2, 1, 1, 1, 0);
		errorsAnswersLabel = new JLabel();
		builder.add(errorsAnswersLabel, 0, 3, 1, 1, 1, 0);
	
		resultsArea.setLineWrap(true);
		resultsArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultsArea);
		builder.add(scrollPane, 0, 4, 2, 1, 1, 1);
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		correctAnswersLabel.setText("Risposte corrette: "+qaManager.getResults().getnResult(settings.getCorrectResultType()));
		blankAnswersLabel.setText("Risposte bianche: "+qaManager.getResults().getnResult(settings.getBlankResultType()));
		errorsAnswersLabel.setText("Risposte sbagliate: "+qaManager.getResults().getnResult(settings.getErrorResultType()));
		shownResultGroup.clearSelection();
		resultsArea.setText("");
	}
}
