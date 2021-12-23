package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import qaCore.QuestionAndAnswers;
import qaCore.Settings;
import utils.GridBagBuilder;
/**
 * The panel that contains the answers
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class AnswersPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;	
	private GridBagBuilder builder = new GridBagBuilder(this);
	
	private Settings settings = Settings.getSettings();
	
	private TestPanel testPanel;
	private ButtonGroup answersGroup = new ButtonGroup();

	/**
	 * Create a new panel for the answers
	 * @param testPanel The panel that contains the test
	 */
	public AnswersPanel(TestPanel testPanel) {
		super();
		this.testPanel = testPanel;
	}
	
	public String getUserAnswer() {
		return answersGroup.getSelection().getActionCommand();
	}
	/**
	 * Create the answers of the input question
	 * @param questionAndAnswers The question of which formulating the answers
	 */
	public void createAnswers(final QuestionAndAnswers questionAndAnswers) {
		this.removeAll();
		this.revalidate();
		answersGroup.clearSelection();
		int yPosition = 0;
		for (Map.Entry<String, String> answer : questionAndAnswers.getAnswers().entrySet()) {
			final JRadioButton answerRadioButton = new JRadioButton();
			answerRadioButton.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth(), settings.getButtonHeight()>>1));
			answerRadioButton.setActionCommand(answer.getKey());
			answerRadioButton.setText("<html>"+answer.getValue()+"</html>");
			builder.add(answerRadioButton, 0, yPosition, 1, 1, 1, 0);
			answersGroup.add(answerRadioButton);
			yPosition++;
			answerRadioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (answerRadioButton.isSelected() && settings.isAutomaticNext()) {
						questionAndAnswers.setUserAnswer(answerRadioButton.getActionCommand());
						testPanel.nextQuestion();
					}						
				}
			});
			if (questionAndAnswers.getUserAnswer().equals(answerRadioButton.getActionCommand())) {
				answerRadioButton.setSelected(true);
			}
		}
	}
}
